package bookGenerator.problem.viewHandler;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import bookGenerator._global.config.kafka.KafkaProcessor;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;

import bookGenerator.problem.domain.Problem;
import bookGenerator.problem.event.ProblemCreated;

@Service
public class WhenProblemCreated_CreateProblem_ViewHandler {

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='ProblemCreated'"
    )
    public void whenProblemCreated_CreateProblem_ViewHandler(
        @Payload ProblemCreated problemCreated
    ) {
        try {

            CustomLogger.debugObject(CustomLoggerType.ENTER, problemCreated);


            Problem.repository().save(
                Problem.createWithObject(problemCreated)
            );


            CustomLogger.debug(CustomLoggerType.EXIT);

        } catch (Exception e) {
            CustomLogger.errorObject(e, problemCreated);
        }
    }
    
}
