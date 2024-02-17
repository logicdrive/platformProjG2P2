package bookGenerator.book.viewHandler;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import bookGenerator._global.config.kafka.KafkaProcessor;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;

import bookGenerator.book.domain.Book;
import bookGenerator.book.domain.BookManageService;
import bookGenerator.book.event.BookDeleted;

@Service
public class WhenBookDeleted_DeleteBook_ViewHandler {

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='BookDeleted'"
    )
    public void WhenBookDeleted_DeleteBook_ViewHandler(
        @Payload BookDeleted bookDeleted
    ) {
        try {

            CustomLogger.debugObject(CustomLoggerType.ENTER, bookDeleted);


            Book.repository().delete(
                BookManageService.getInstance().findByBookId(bookDeleted.getId())
            );


            CustomLogger.debug(CustomLoggerType.EXIT);

        } catch (Exception e) {
            CustomLogger.errorObject(e, bookDeleted);
        }
    }
    
}
