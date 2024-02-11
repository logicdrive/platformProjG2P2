package bookGenerator.policy;

import javax.transaction.Transactional;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import bookGenerator._global.config.kafka.KafkaProcessor;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;
import bookGenerator._global.event.ProblemGenerationFailed;

@Service
@Transactional
public class ProblemGenerationFailed_deleteProblemByFail_Policy {

    // ProblemGenerationFailed 이벤트 발생 관련 정책
    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='ProblemGenerationFailed'"
    )
    public void problemGenerationFailed_deleteProblemByFail_Policy(
        @Payload ProblemGenerationFailed problemGenerationFailed
    ) {
        try
        {
                
            CustomLogger.debugObject(CustomLoggerType.ENTER, problemGenerationFailed);
            

            CustomLogger.debug(CustomLoggerType.EXIT); 


        } catch(Exception e) {
            CustomLogger.errorObject(e, "", problemGenerationFailed);        
        }
    }

}
