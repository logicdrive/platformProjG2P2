package bookGenerator.bookShelfBook.viewHandler;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import bookGenerator._global.config.kafka.KafkaProcessor;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;

import bookGenerator.bookShelfBook.domain.BookShelfBook;
import bookGenerator.bookShelfBook.domain.BookShelfBookManageService;
import bookGenerator.bookShelfBook.event.BookShelfBookDeleted;

@Service
public class WhenBookShelfBookDeleted_DeleteBookShelfBook_ViewHandler {

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='BookShelfBookDeleted'"
    )
    public void whenBookShelfBookDeleted_DeleteBookShelfBook_ViewHandler(
        @Payload BookShelfBookDeleted bookShelfBookDeleted
    ) {
        try {

            CustomLogger.debugObject(CustomLoggerType.ENTER, bookShelfBookDeleted);


            BookShelfBook.repository().delete(
                BookShelfBookManageService.getInstance().findByBookShelfBookId(bookShelfBookDeleted.getId())
            );


            CustomLogger.debug(CustomLoggerType.EXIT);

        } catch (Exception e) {
            CustomLogger.errorObject(e, bookShelfBookDeleted);
        }
    }
    
}
