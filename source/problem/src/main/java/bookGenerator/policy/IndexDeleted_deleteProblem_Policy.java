package bookGenerator.policy;

import javax.transaction.Transactional;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import bookGenerator._global.config.kafka.KafkaProcessor;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;
import bookGenerator._global.event.IndexDeleted;

@Service
@Transactional
public class IndexDeleted_deleteProblem_Policy {

    // IndexDeleted 이벤트 발생 관련 정책
    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='IndexDeleted'"
    )
    public void indexDeleted_deleteProblem_Policy(
        @Payload IndexDeleted indexDeleted
    ) {
        try
        {

            CustomLogger.debugObject(CustomLoggerType.ENTER, indexDeleted);
            

            CustomLogger.debug(CustomLoggerType.EXIT);

        } catch(Exception e) {
            CustomLogger.errorObject(e, "", indexDeleted);        
        }
    }

}
