package bookGenerator.policy;

import java.util.List;
import javax.transaction.Transactional;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import bookGenerator._global.config.kafka.KafkaProcessor;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;
import bookGenerator.domain.BookShelfBook;
import bookGenerator.domain.BookShelfBookManageService;
import bookGenerator._global.event.BookDeleted;
import bookGenerator._global.event.BookShelfBookDeleted;

import bookGenerator.domain.BookShelfBook;
import bookGenerator.domain.BookShelfBookManageService;


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


            // [1] bookId가 일치하는 BookShelfBook을 삭제한다.
            BookShelfBook bookShelfBookToDelete = BookShelfBookManageService.getInstance().findByBookIdOrThrow(bookDeleted.getId());
            BookShelfBook.repository().delete(bookShelfBookToDelete);

            // [2] BookShelfBookDeleted 이벤트를 삭제된 BookShelfBook을 이용해서 발생시킨다.
            (new BookShelfBookDeleted(bookShelfBookToDelete)).publish();


            BookShelfBook bookShelfBookToDelete = BookShelfBookManageService.getInstance().findByBookIdOrThrow(bookDeleted.getId());
            BookShelfBook.repository().delete(bookShelfBookToDelete);
            
            (new BookShelfBookDeleted(bookShelfBookToDelete)).publish();



            CustomLogger.debug(CustomLoggerType.EXIT);

        } catch(Exception e) {
            CustomLogger.errorObject(e, "", bookDeleted);        
        }
    }

}
