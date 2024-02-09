package bookGenerator.policy;

import javax.transaction.Transactional;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import bookGenerator._global.config.kafka.KafkaProcessor;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;
import bookGenerator._global.event.CoverImageGenerationRequested;

@Service
@Transactional
public class CoverImageGenerationRequested_requestCoverImageGeneration_Policy {

    // CoverImageGenerationRequested 이벤트 발생 관련 정책
    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='CoverImageGenerationRequested'"
    )
    public void coverImageGenerationRequested_requestCoverImageGeneration_Policy(
        @Payload CoverImageGenerationRequested coverImageGenerationRequested
    ) {
        try
        {
            
            CustomLogger.debugObject(CustomLoggerType.ENTER_EXIT, "CoverImageGenerationRequested", coverImageGenerationRequested);

        } catch(Exception e) {
            CustomLogger.errorObject(e, "", coverImageGenerationRequested);        
        }
    }

}
