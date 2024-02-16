package bookGenerator.policy;

import javax.transaction.Transactional;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import bookGenerator._global.config.kafka.KafkaProcessor;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;
import bookGenerator.domain.File;
import bookGenerator.domain.FileManageService;
import bookGenerator._global.event.CoverImageInfoUpdated;
import bookGenerator._global.event.CoverImageUploaded;

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

            // [1] fileId에 해당하는 File 정보를 조회함
            File FileToFind = FileManageService.getInstance().findByIdOrThrow(coverImageUploaded.getFileId());

            // [2] 조회된 File 객체의 url을 coverImageUploaded.fileUrl로 변경하고 저장함
            FileToFind.setUrl(coverImageUploaded.getFileUrl());
            File.repository().save(FileToFind);

            // [3] 조회된 File 객체로 CoverImageInfoUpdated 이벤트를 발생시킴
            (new CoverImageInfoUpdated(FileToFind)).publish();

            CustomLogger.debug(CustomLoggerType.EXIT);

        } catch(Exception e) {
            CustomLogger.errorObject(e, "", coverImageUploaded);        
        }
    }

}
