package bookGenerator.policy;

import javax.transaction.Transactional;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import bookGenerator._global.config.kafka.KafkaProcessor;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;
import bookGenerator._global.event.ProblemGenereated;

@Service
@Transactional
public class ProblemGenereated_updateProblem_Policy {

    // ProblemGenereated 이벤트 발생 관련 정책
    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='ProblemGenereated'"
    )
    public void problemGenereated_updateProblem_Policy(
        @Payload ProblemGenereated problemGenereated
    ) {
        try
        {

            CustomLogger.debugObject(CustomLoggerType.ENTER, problemGenereated);
            

            CustomLogger.debug(CustomLoggerType.EXIT);

        } catch(Exception e) {
            CustomLogger.errorObject(e, "", problemGenereated);        
        }
    }

}
