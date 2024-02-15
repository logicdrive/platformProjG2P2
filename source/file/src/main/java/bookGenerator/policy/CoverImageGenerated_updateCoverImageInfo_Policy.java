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
import bookGenerator._global.event.CoverImageGenerated;
import bookGenerator._global.event.CoverImageInfoUpdated;

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

            // [1] fileId에 해당하는 File 정보를 조회함
            File FileToFind = FileManageService.getInstance().findByIdOrThrow(coverImageGenerated.getFileId());
            // [2] 조회된 File 객체의 url을 coverImageGenerated.fileUrl로 변경하고 저장함
            FileToFind.setUrl(coverImageGenerated.getFileUrl());
            File.repository().save(FileToFind);
            // [3] 조회된 File 객체로 CoverImageInfoUpdated 이벤트를 발생시킴
            (new CoverImageInfoUpdated(FileToFind)).publish();
            
            CustomLogger.debug(CustomLoggerType.EXIT);

        } catch(Exception e) {
            CustomLogger.errorObject(e, "", coverImageGenerated);        
        }
    }

}
