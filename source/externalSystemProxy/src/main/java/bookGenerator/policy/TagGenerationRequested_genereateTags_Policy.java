package bookGenerator.policy;

import javax.transaction.Transactional;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import bookGenerator._global.config.kafka.KafkaProcessor;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;
import bookGenerator._global.event.TagGenerationRequested;

@Service
@Transactional
public class TagGenerationRequested_genereateTags_Policy {

    // TagGenerationRequested 이벤트 발생 관련 정책
    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='TagGenerationRequested'"
    )
    public void tagGenerationRequested_genereateTags_Policy(
        @Payload TagGenerationRequested tagGenerationRequested
    ) {
        try
        {
            
            CustomLogger.debugObject(CustomLoggerType.ENTER_EXIT, "TagGenerationRequested", tagGenerationRequested);

        } catch(Exception e) {
            CustomLogger.errorObject(e, "", tagGenerationRequested);        
        }
    }

}
