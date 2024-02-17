package bookGenerator.bookShelf.viewHandler;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import bookGenerator._global.config.kafka.KafkaProcessor;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;

import bookGenerator.bookShelf.domain.BookShelf;
import bookGenerator.bookShelf.domain.BookShelfManageService;
import bookGenerator.bookShelf.event.BookShelfDeleted;

@Service
public class WhenBookShelfDeleted_DeleteBookShelf_ViewHandler {

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='BookShelfDeleted'"
    )
    public void whenBookShelfDeleted_DeleteBookShelf_ViewHandler(
        @Payload BookShelfDeleted bookShelfDeleted
    ) {
        try {

            CustomLogger.debugObject(CustomLoggerType.ENTER, bookShelfDeleted);


            BookShelf.repository().delete(
                BookShelfManageService.getInstance().findByBookShelfId(bookShelfDeleted.getId())
            );


            CustomLogger.debug(CustomLoggerType.EXIT);

        } catch (Exception e) {
            CustomLogger.errorObject(e, bookShelfDeleted);
        }
    }
    
}
