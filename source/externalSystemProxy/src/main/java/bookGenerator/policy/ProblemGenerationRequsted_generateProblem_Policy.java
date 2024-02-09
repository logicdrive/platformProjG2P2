package bookGenerator.policy;

import javax.transaction.Transactional;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import bookGenerator._global.config.kafka.KafkaProcessor;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;
import bookGenerator._global.event.ProblemGenerationRequsted;

@Service
@Transactional
public class ProblemGenerationRequsted_generateProblem_Policy {

    // ProblemGenerationRequsted 이벤트 발생 관련 정책
    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='ProblemGenerationRequsted'"
    )
    public void problemGenerationRequsted_generateProblem_Policy(
        @Payload ProblemGenerationRequsted problemGenerationRequsted
    ) {
        try
        {
            
            CustomLogger.debugObject(CustomLoggerType.ENTER_EXIT, "ProblemGenerationRequsted", problemGenerationRequsted);

        } catch(Exception e) {
            CustomLogger.errorObject(e, "", problemGenerationRequsted);        
        }
    }

}
