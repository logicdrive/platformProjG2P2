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
public class ProblemGenereated_createProblem_Policy {

    // AI 기반 문제 생성을 요청해서 문제 생성 이벤트가 발생했을 경우, 해당 이벤트를 기반으로 문제를 새로 생성시키기 위한 정책
    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='ProblemGenereated'"
    )
    public void problemGenereated_createProblem_Policy(
        @Payload ProblemGenereated problemGenereated
    ) {
        try
        {

            CustomLogger.debugObject(CustomLoggerType.ENTER, problemGenereated);
            
            // [1] 새로운 Problem 객체를 생성
            // [!] indexId, content, answer, priority만 초기화시키면 되며, 다른 변수들은 자동으로 초기화됨

            // [2] 생성된 Problem을 기반으로 ProblemCreated 이벤트를 발생시킴

            CustomLogger.debug(CustomLoggerType.EXIT);

        } catch(Exception e) {
            CustomLogger.errorObject(e, "", problemGenereated);        
        }
    }

}