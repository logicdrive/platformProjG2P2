package bookGenerator.policy;

import javax.transaction.Transactional;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import bookGenerator._global.config.kafka.KafkaProcessor;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;
import bookGenerator._global.event.EmptyCoverImageInfoCreated;

@Service
@Transactional
public class EmptyCoverImageInfoCreated_UpdateCoverImageFileId_Policy {

    // E-Book 표지 이미지 파일이 생성되었을 경우, 관련 파일 ID를 업데이트하기 위해서
    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='EmptyCoverImageInfoCreated'"
    )
    public void emptyCoverImageInfoCreated_UpdateCoverImageFileId_Policy(
        @Payload EmptyCoverImageInfoCreated emptyCoverImageInfoCreated
    ) {
        try
        {
            CustomLogger.debugObject(CustomLoggerType.ENTER, "EmptyCoverImageInfoCreated", emptyCoverImageInfoCreated);
            
            // [1] bookId로 Book 객체를 찾음

            // [2] Book 객체의 coverImageFileId를 업데이트

            // [3] Book 객체로 CoverImageFileIdUpdated 이벤트를 발생시킴

            CustomLogger.debug(CustomLoggerType.EXIT);

        } catch(Exception e) {
            CustomLogger.errorObject(e, "", emptyCoverImageInfoCreated);        
        }
    }

}
