package bookGenerator.policy;

import javax.transaction.Transactional;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import bookGenerator._global.config.kafka.KafkaProcessor;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;
import bookGenerator._global.event.BookDeleted;
import bookGenerator._global.event.BookShelfBookDeleted;

import bookGenerator.domain.BookShelfBook;

@Service
@Transactional
public class BookDeleted_deleteBookShelfBook_Policy {

    // Book이 삭제되었을시에 연관된 BookShelfBook을 삭제시키는 정책
    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='BookDeleted'"
    )
    public void bookDeleted_deleteBookShelfBook_Policy(
        @Payload BookDeleted bookDeleted
    ) {
        try
        {
            
            CustomLogger.debugObject(CustomLoggerType.ENTER, bookDeleted);

            
            BookShelfBook.repository().findByBookId(bookDeleted.getId()).forEach(bookShelfBookToDelete -> {
                BookShelfBook.repository().delete(bookShelfBookToDelete);
                (new BookShelfBookDeleted(bookShelfBookToDelete)).publish();
            });


            CustomLogger.debug(CustomLoggerType.EXIT);

        } catch(Exception e) {
            CustomLogger.errorObject(e, "", bookDeleted);        
        }
    }

}
