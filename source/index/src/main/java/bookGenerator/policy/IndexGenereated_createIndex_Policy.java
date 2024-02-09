package bookGenerator.policy;

import javax.transaction.Transactional;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import bookGenerator._global.config.kafka.KafkaProcessor;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;
import bookGenerator._global.event.IndexGenereated;

@Service
@Transactional
public class IndexGenereated_createIndex_Policy {

    // IndexGenereated 이벤트 발생 관련 정책
    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='IndexGenereated'"
    )
    public void indexGenereated_createIndex_Policy(
        @Payload IndexGenereated indexGenereated
    ) {
        try
        {
            
            CustomLogger.debugObject(CustomLoggerType.ENTER_EXIT, "IndexGenereated", indexGenereated);

        } catch(Exception e) {
            CustomLogger.errorObject(e, "", indexGenereated);        
        }
    }

}
