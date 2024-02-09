package bookGenerator.policy;

import javax.transaction.Transactional;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import bookGenerator._global.config.kafka.KafkaProcessor;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;
import bookGenerator._global.event.ContentmageGenerationFailed;

@Service
@Transactional
public class ContentmageGenerationFailed_deleteContentByFail_Policy {

    // ContentmageGenerationFailed 이벤트 발생 관련 정책
    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='ContentmageGenerationFailed'"
    )
    public void contentmageGenerationFailed_deleteContentByFail_Policy(
        @Payload ContentmageGenerationFailed contentmageGenerationFailed
    ) {
        try
        {
            
            CustomLogger.debugObject(CustomLoggerType.ENTER_EXIT, "ContentmageGenerationFailed", contentmageGenerationFailed);

        } catch(Exception e) {
            CustomLogger.errorObject(e, "", contentmageGenerationFailed);        
        }
    }

}
