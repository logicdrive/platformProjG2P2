package bookGenerator.policy;

import javax.transaction.Transactional;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import bookGenerator._global.config.kafka.KafkaProcessor;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;
import bookGenerator._global.event.ProblemCreated;
import bookGenerator._global.event.ProblemGenerated;

import bookGenerator.domain.Problem;

@Service
@Transactional
public class ProblemGenereated_createProblem_Policy {

    // AI 기반 문제 생성을 요청해서 문제 생성 이벤트가 발생했을 경우, 해당 이벤트를 기반으로 문제를 새로 생성시키기 위한 정책
    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='ProblemGenerated'"
    )
    public void problemGenereated_createProblem_Policy(
        @Payload ProblemGenerated problemGenerated
    ) {
        try
        {

            CustomLogger.debugObject(CustomLoggerType.ENTER, problemGenerated);
            

            Problem savedProblem = Problem.repository().save(
                Problem.builder()
                    .indexId(problemGenerated.getIndexId())
                    .content(problemGenerated.getContent())
                    .answer(problemGenerated.getAnswer())
                    .priority(problemGenerated.getPriority())
                    .build()
            );
            (new ProblemCreated(savedProblem)).publish();


            CustomLogger.debug(CustomLoggerType.EXIT);

        } catch(Exception e) {
            CustomLogger.errorObject(e, "", problemGenerated);        
        }
    }

}