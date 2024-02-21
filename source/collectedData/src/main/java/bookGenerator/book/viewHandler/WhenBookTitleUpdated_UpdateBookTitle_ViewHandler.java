package bookGenerator.book.viewHandler;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import bookGenerator._global.config.kafka.KafkaProcessor;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;

import bookGenerator.book.domain.Book;
import bookGenerator.book.domain.BookManageService;
import bookGenerator.book.event.BookTitleUpdated;
import bookGenerator.webSocket.WebSocketEventHandler;

@Service
public class WhenBookTitleUpdated_UpdateBookTitle_ViewHandler {

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='BookTitleUpdated'"
    )
    public void whenBookTitleUpdated_UpdateBookTitle_ViewHandler(
        @Payload BookTitleUpdated bookTitleUpdated
    ) {
        try {

            CustomLogger.debugObject(CustomLoggerType.ENTER, bookTitleUpdated);


            Book.repository().save(
                BookManageService.getInstance().findByBookId(bookTitleUpdated.getId())
                    .copyAllProperties(bookTitleUpdated)
            );


            CustomLogger.debug(CustomLoggerType.EXIT);
            WebSocketEventHandler.getInstance().notifyEventsToSpecificUser(
                bookTitleUpdated.getCreaterId(), "BookTitleUpdated", 
                String.format("{\"bookId\": %d}", bookTitleUpdated.getId())
            );

        } catch (Exception e) {
            CustomLogger.errorObject(e, bookTitleUpdated);
        }
    }
    
}
