package bookGenerator.policy;

import javax.transaction.Transactional;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import bookGenerator._global.config.kafka.KafkaProcessor;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;
import bookGenerator.domain.BookShelf;
import bookGenerator.domain.BookShelfManageService;
import bookGenerator._global.event.BookCountUpdated;
import bookGenerator._global.event.BookShelfBookAdded;

@Service
@Transactional
public class BookShelfBookAdded_increaseBookCount_Policy {

    // BookShelf에 새로운 책이 추가되었을 경우, 개수를 올리는 정책
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


            BookShelf bookShelf = BookShelfManageService.getInstance().findByIdOrThrow(bookShelfBookAdded.getBookShelfId());
            bookShelf.setBookCount(bookShelf.getBookCount() + 1);
            BookShelf.repository().save(bookShelf);

            (new BookCountUpdated(bookShelf)).publish();
            

            CustomLogger.debug(CustomLoggerType.EXIT);

        } catch(Exception e) {
            CustomLogger.errorObject(e, "", bookShelfBookAdded);        
        }
    }

}
