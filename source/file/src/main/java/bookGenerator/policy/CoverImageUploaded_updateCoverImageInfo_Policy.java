package bookGenerator.policy;

import javax.transaction.Transactional;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import bookGenerator._global.config.kafka.KafkaProcessor;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;
import bookGenerator._global.event.CoverImageUploaded;

@Service
@Transactional
public class CoverImageUploaded_updateCoverImageInfo_Policy {

    // CoverImageUploaded 이벤트 발생 관련 정책
    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='CoverImageUploaded'"
    )
    public void coverImageUploaded_updateCoverImageInfo_Policy(
        @Payload CoverImageUploaded coverImageUploaded
    ) {
        try
        {
            
            CustomLogger.debugObject(CustomLoggerType.ENTER_EXIT, "CoverImageUploaded", coverImageUploaded);

        } catch(Exception e) {
            CustomLogger.errorObject(e, "", coverImageUploaded);        
        }
    }

}
