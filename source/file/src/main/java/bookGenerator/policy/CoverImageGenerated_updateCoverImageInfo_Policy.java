package bookGenerator.policy;

import javax.transaction.Transactional;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import bookGenerator._global.config.kafka.KafkaProcessor;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;
import bookGenerator._global.event.CoverImageGenerated;
import bookGenerator._global.event.CoverImageInfoUpdated;

import bookGenerator.domain.File;
import bookGenerator.domain.FileManageService;

@Service
@Transactional
public class CoverImageGenerated_updateCoverImageInfo_Policy {

    // Cover Image가 생성되면 관련 Url을 File 정보에 반영하고 업데이트 이벤트를 발생시키는 정책
    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='CoverImageGenerated'"
    )
    public void coverImageGenerated_updateCoverImageInfo_Policy(
        @Payload CoverImageGenerated coverImageGenerated
    ) {
        try
        {

            CustomLogger.debugObject(CustomLoggerType.ENTER, coverImageGenerated);


            File searchedFile = FileManageService.getInstance().findByIdOrThrow(coverImageGenerated.getFileId());
            searchedFile.setUrl(coverImageGenerated.getFileUrl());
            File.repository().save(searchedFile);

            (new CoverImageInfoUpdated(searchedFile)).publish();

            
            CustomLogger.debug(CustomLoggerType.EXIT);

        } catch(Exception e) {
            CustomLogger.errorObject(e, "", coverImageGenerated);        
        }
    }

}
