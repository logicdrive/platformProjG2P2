package bookGenerator.policy;

import javax.transaction.Transactional;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import bookGenerator._global.config.kafka.KafkaProcessor;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;
import bookGenerator._global.event.BookShelfBookAdded;

@Service
@Transactional
public class BookShelfBookAdded_IncreaseBookCount_Policy {

    // BookShelfBookAdded 이벤트 발생 관련 정책
    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='BookShelfBookAdded'"
    )
    public void bookShelfBookAdded_IncreaseBookCount_Policy(
        @Payload BookShelfBookAdded bookShelfBookAdded
    ) {
        try
        {
            
            CustomLogger.debugObject(CustomLoggerType.ENTER, bookShelfBookAdded);

            
            CustomLogger.debug(CustomLoggerType.EXIT);

        } catch(Exception e) {
            CustomLogger.errorObject(e, "", bookShelfBookAdded);        
        }
    }

}
