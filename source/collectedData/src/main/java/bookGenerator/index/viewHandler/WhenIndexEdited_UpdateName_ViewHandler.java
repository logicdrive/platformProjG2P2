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
import bookGenerator.index.event.IndexEdited;
import bookGenerator.webSocket.WebSocketEventHandler;

@Service
public class WhenIndexEdited_UpdateName_ViewHandler {

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='IndexEdited'"
    )
    public void whenIndexEdited_UpdateName_ViewHandler(
        @Payload IndexEdited indexEdited
    ) {
        try {

            CustomLogger.debugObject(CustomLoggerType.ENTER, indexEdited);


            Index.repository().save(
                IndexManageService.getInstance().findByIndexId(indexEdited.getId())
                    .copyAllProperties(indexEdited)
            );


            CustomLogger.debug(CustomLoggerType.EXIT);
            WebSocketEventHandler.getInstance().notifyEventsToSpecificUser(
                BookManageService.getInstance().findByBookId(indexEdited.getBookId()).getCreaterId(), 
                "IndexEdited", 
                String.format("{\"indexId\": %d}", indexEdited.getId())
            );

        } catch (Exception e) {
            CustomLogger.errorObject(e, indexEdited);
        }
    }
    
}
