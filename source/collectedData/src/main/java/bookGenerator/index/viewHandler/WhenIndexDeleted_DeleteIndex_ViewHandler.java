package bookGenerator.index.viewHandler;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import bookGenerator._global.config.kafka.KafkaProcessor;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;
import bookGenerator.book.domain.BookManageService;
import bookGenerator.index.domain.Index;
import bookGenerator.index.domain.IndexManageService;
import bookGenerator.index.event.IndexDeleted;
import bookGenerator.webSocket.WebSocketEventHandler;

@Service
public class WhenIndexDeleted_DeleteIndex_ViewHandler {

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='IndexDeleted'"
    )
    public void whenIndexDeleted_DeleteIndex_ViewHandler(
        @Payload IndexDeleted indexDeleted
    ) {
        try {

            CustomLogger.debugObject(CustomLoggerType.ENTER, indexDeleted);


            Index.repository().delete(
                IndexManageService.getInstance().findByIndexId(indexDeleted.getId())
            );


            CustomLogger.debug(CustomLoggerType.EXIT);
            try {

                WebSocketEventHandler.getInstance().notifyEventsToSpecificUser(
                    BookManageService.getInstance().findByBookId(indexDeleted.getBookId()).getCreaterId(), 
                    "IndexDeleted", 
                    String.format("{\"indexId\": %d}", indexDeleted.getId())
                );

            } catch (Exception e) {
                CustomLogger.debug(CustomLoggerType.EFFECT, e.getMessage());
            }

        } catch (Exception e) {
            CustomLogger.errorObject(e, indexDeleted);
        }
    }
    
}
