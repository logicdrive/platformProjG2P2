package bookGenerator.bookShelf.viewHandler;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import bookGenerator._global.config.kafka.KafkaProcessor;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;

import bookGenerator.bookShelf.domain.BookShelf;
import bookGenerator.bookShelf.event.BookShelfCreated;

@Service
public class WhenBookShelfCreated_CreateBookShelf_ViewHandler {

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='BookShelfCreated'"
    )
    public void whenBookShelfCreated_CreateBookShelf_ViewHandler(
        @Payload BookShelfCreated bookShelfCreated
    ) {
        try {

            CustomLogger.debugObject(CustomLoggerType.ENTER, bookShelfCreated);


            BookShelf.repository().save(
                BookShelf.createWithObject(bookShelfCreated)
            );


            CustomLogger.debug(CustomLoggerType.EXIT);

        } catch (Exception e) {
            CustomLogger.errorObject(e, bookShelfCreated);
        }
    }
    
}
