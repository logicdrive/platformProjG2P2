package bookGenerator.book.viewHandler;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import bookGenerator._global.config.kafka.KafkaProcessor;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;

import bookGenerator.book.domain.Book;
import bookGenerator.book.event.EmptyBookCreated;
import bookGenerator.webSocket.WebSocketEventHandler;

@Service
public class WhenEmptyBookCreated_CreateBook_ViewHandler {

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='EmptyBookCreated'"
    )
    public void whenEmptyBookCreated_CreateBook_ViewHandler(
        @Payload EmptyBookCreated emptyBookCreated
    ) {
        try {

            CustomLogger.debugObject(CustomLoggerType.ENTER, emptyBookCreated);


            Book.repository().save(
                Book.createWithObject(emptyBookCreated)
            );


            CustomLogger.debug(CustomLoggerType.EXIT);
            WebSocketEventHandler.getInstance().notifyEventsToSpecificUser(
                emptyBookCreated.getCreaterId(), "EmptyBookCreated", 
                String.format("{\"bookId\": %d}", emptyBookCreated.getId())
            );

        } catch (Exception e) {
            CustomLogger.errorObject(e, emptyBookCreated);
        }
    }
    
}
