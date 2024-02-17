package bookGenerator.bookShelfBook.viewHandler;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import bookGenerator._global.config.kafka.KafkaProcessor;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;

import bookGenerator.bookShelfBook.domain.BookShelfBook;
import bookGenerator.bookShelfBook.event.BookShelfBookAdded;

@Service
public class WhenBookShelfBookAdded_CreateBookShelfBook_ViewHandler {

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='BookShelfBookAdded'"
    )
    public void whenBookShelfBookAdded_CreateBookShelfBook_ViewHandler(
        @Payload BookShelfBookAdded bookShelfBookAdded
    ) {
        try {

            CustomLogger.debugObject(CustomLoggerType.ENTER, bookShelfBookAdded);


            BookShelfBook.repository().save(
                BookShelfBook.createWithObject(bookShelfBookAdded)
            );


            CustomLogger.debug(CustomLoggerType.EXIT);

        } catch (Exception e) {
            CustomLogger.errorObject(e, bookShelfBookAdded);
        }
    }
    
}
