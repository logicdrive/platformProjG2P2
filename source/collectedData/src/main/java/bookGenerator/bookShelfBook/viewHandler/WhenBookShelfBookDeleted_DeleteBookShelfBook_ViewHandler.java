package bookGenerator.bookShelfBook.viewHandler;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import bookGenerator._global.config.kafka.KafkaProcessor;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;
import bookGenerator.bookShelf.domain.BookShelfManageService;
import bookGenerator.bookShelfBook.domain.BookShelfBook;
import bookGenerator.bookShelfBook.domain.BookShelfBookManageService;
import bookGenerator.bookShelfBook.event.BookShelfBookDeleted;
import bookGenerator.webSocket.WebSocketEventHandler;

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
            try {

                WebSocketEventHandler.getInstance().notifyEventsToSpecificUser(
                    BookShelfManageService.getInstance().findByBookShelfId(
                        bookShelfBookDeleted.getBookShelfId()
                    ).getCreaterId(),
                    "BookShelfBookDeleted", 
                    String.format("{\"bookShelfBookId\": %d}", bookShelfBookDeleted.getId())
                );

            } catch (Exception e) {
                CustomLogger.debug(CustomLoggerType.EFFECT, e.getMessage());
            }

        } catch (Exception e) {
            CustomLogger.errorObject(e, bookShelfBookDeleted);
        }
    }
    
}
