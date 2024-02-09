package bookGenerator.policy;

import javax.transaction.Transactional;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import bookGenerator._global.config.kafka.KafkaProcessor;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;
import bookGenerator._global.event.CoverImageUpdateRequested;

@Service
@Transactional
public class CoverImageUpdateRequested_requestCoverImageUpdate_Policy {

    // CoverImageUpdateRequested 이벤트 발생 관련 정책
    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='CoverImageUpdateRequested'"
    )
    public void coverImageUpdateRequested_requestCoverImageUpdate_Policy(
        @Payload CoverImageUpdateRequested coverImageUpdateRequested
    ) {
        try
        {
            
            CustomLogger.debugObject(CustomLoggerType.ENTER_EXIT, "CoverImageUpdateRequested", coverImageUpdateRequested);

        } catch(Exception e) {
            CustomLogger.errorObject(e, "", coverImageUpdateRequested);        
        }
    }

}
