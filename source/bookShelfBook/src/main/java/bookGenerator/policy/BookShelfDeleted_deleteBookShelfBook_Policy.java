package bookGenerator.policy;

import javax.transaction.Transactional;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import bookGenerator._global.config.kafka.KafkaProcessor;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;
import bookGenerator._global.event.BookShelfDeleted;

@Service
@Transactional
public class BookShelfDeleted_deleteBookShelfBook_Policy {

    // BookShelf가 삭제되었을시에 속해져 있었던 BookShelfBook들도 함께 삭제시키는 정책
    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='BookShelfDeleted'"
    )
    public void bookShelfDeleted_deleteBookShelfBook_Policy(
        @Payload BookShelfDeleted bookShelfDeleted
    ) {
        try
        {
            
            CustomLogger.debugObject(CustomLoggerType.ENTER, bookShelfDeleted);

            // [1] bookShelfId가 일치하는 BookShelfBook들을 삭제한다.

            CustomLogger.debug(CustomLoggerType.EXIT);

        } catch(Exception e) {
            CustomLogger.errorObject(e, "", bookShelfDeleted);        
        }
    }

}
