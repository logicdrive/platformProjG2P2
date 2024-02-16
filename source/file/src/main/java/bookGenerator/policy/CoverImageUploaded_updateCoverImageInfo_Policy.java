package bookGenerator.policy;

import javax.transaction.Transactional;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import bookGenerator._global.config.kafka.KafkaProcessor;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;
import bookGenerator._global.event.CoverImageInfoUpdated;
import bookGenerator._global.event.CoverImageUploaded;

import bookGenerator.domain.File;
import bookGenerator.domain.FileManageService;

@Service
@Transactional
public class CoverImageUploaded_updateCoverImageInfo_Policy {

    // 업로드된 Cover Image가 업데이트될 경우, 관련 Url을 파일 정보에 반영시키는 정책
    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='CoverImageUploaded'"
    )
    public void coverImageUploaded_updateCoverImageInfo_Policy(
        @Payload CoverImageUploaded coverImageUploaded
    ) {
        try
        {

            CustomLogger.debugObject(CustomLoggerType.ENTER, coverImageUploaded);


            File searchedFile = FileManageService.getInstance().findByIdOrThrow(coverImageUploaded.getFileId());
            searchedFile.setUrl(coverImageUploaded.getFileUrl());
            File.repository().save(searchedFile);

            (new CoverImageInfoUpdated(searchedFile)).publish();


            CustomLogger.debug(CustomLoggerType.EXIT);

        } catch(Exception e) {
            CustomLogger.errorObject(e, "", coverImageUploaded);        
        }
    }

}
