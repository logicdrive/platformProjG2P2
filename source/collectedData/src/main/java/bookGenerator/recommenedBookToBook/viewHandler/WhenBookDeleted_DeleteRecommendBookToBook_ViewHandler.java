package bookGenerator.recommenedBookToBook.viewHandler;

import javax.transaction.Transactional;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import bookGenerator._global.config.kafka.KafkaProcessor;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;

import bookGenerator.book.event.BookDeleted;
import bookGenerator.recommenedBookToBook.domain.RecommenedBookToBook;

@Service
public class WhenBookDeleted_DeleteRecommendBookToBook_ViewHandler {

    @Transactional
    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='BookDeleted'"
    )
    public void whenBookDeleted_DeleteRecommendBookToBook_ViewHandler(
        @Payload BookDeleted bookDeleted
    ) {
        try {

            CustomLogger.debugObject(CustomLoggerType.ENTER, bookDeleted);


            RecommenedBookToBook.repository().deleteByBookId(bookDeleted.getId());
            RecommenedBookToBook.repository().deleteByRecommendedBookId(bookDeleted.getId());


            CustomLogger.debug(CustomLoggerType.EXIT);

        } catch (Exception e) {
            CustomLogger.errorObject(e, bookDeleted);
        }
    }
    
}
