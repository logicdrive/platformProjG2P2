package bookGenerator.policy;

import javax.transaction.Transactional;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import bookGenerator._global.config.kafka.KafkaProcessor;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;
import bookGenerator._global.event.IndexGenerationRequested;

@Service
@Transactional
public class IndexGenerationRequested_generateIndexes_Policy {

    // IndexGenerationRequested 이벤트 발생 관련 정책
    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='IndexGenerationRequested'"
    )
    public void indexGenerationRequested_generateIndexes_Policy(
        @Payload IndexGenerationRequested indexGenerationRequested
    ) {
        try
        {
            
            CustomLogger.debugObject(CustomLoggerType.ENTER_EXIT, "IndexGenerationRequested", indexGenerationRequested);

        } catch(Exception e) {
            CustomLogger.errorObject(e, "", indexGenerationRequested);        
        }
    }

}
