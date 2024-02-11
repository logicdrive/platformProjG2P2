package bookGenerator.policy;

import javax.transaction.Transactional;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import bookGenerator._global.config.kafka.KafkaProcessor;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;
import bookGenerator._global.event.BookShelfBookDeleted;

@Service
@Transactional
public class BookShelfBookDeleted_decreaseBookCount_Policy {

    // BookShelfBookDeleted 이벤트 발생 관련 정책
    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='BookShelfBookDeleted'"
    )
    public void bookShelfBookDeleted_decreaseBookCount_Policy(
        @Payload BookShelfBookDeleted bookShelfBookDeleted
    ) {
        try
        {
            
            CustomLogger.debugObject(CustomLoggerType.ENTER, bookShelfBookDeleted);

            
            CustomLogger.debug(CustomLoggerType.EXIT);

        } catch(Exception e) {
            CustomLogger.errorObject(e, "", bookShelfBookDeleted);        
        }
    }

}
