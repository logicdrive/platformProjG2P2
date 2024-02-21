package bookGenerator.index.viewHandler;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import bookGenerator._global.config.kafka.KafkaProcessor;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;
import bookGenerator.book.domain.BookManageService;
import bookGenerator.index.domain.Index;
import bookGenerator.index.event.IndexCreated;
import bookGenerator.webSocket.WebSocketEventHandler;

@Service
public class WhenIndexCreated_CreateIndex_ViewHandler {

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='IndexCreated'"
    )
    public void whenIndexCreated_CreateIndex_ViewHandler(
        @Payload IndexCreated indexCreated
    ) {
        try {

            CustomLogger.debugObject(CustomLoggerType.ENTER, indexCreated);


            Index.repository().save(
                Index.createWithObject(indexCreated)
            );


            CustomLogger.debug(CustomLoggerType.EXIT);
            WebSocketEventHandler.getInstance().notifyEventsToSpecificUser(
                BookManageService.getInstance().findByBookId(indexCreated.getBookId()).getCreaterId(), 
                "IndexCreated", 
                String.format("{\"indexId\": %d}", indexCreated.getId())
            );

        } catch (Exception e) {
            CustomLogger.errorObject(e, indexCreated);
        }
    }
    
}
