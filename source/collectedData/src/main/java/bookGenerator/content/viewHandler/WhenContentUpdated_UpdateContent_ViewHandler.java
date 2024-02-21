package bookGenerator.content.viewHandler;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import bookGenerator._global.config.kafka.KafkaProcessor;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;
import bookGenerator.book.domain.BookManageService;
import bookGenerator.content.domain.Content;
import bookGenerator.content.domain.ContentManageService;
import bookGenerator.content.event.ContentUpdated;
import bookGenerator.index.domain.IndexManageService;
import bookGenerator.webSocket.WebSocketEventHandler;

@Service
public class WhenContentUpdated_UpdateContent_ViewHandler {

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='ContentUpdated'"
    )
    public void whenContentUpdated_UpdateContent_ViewHandler(
        @Payload ContentUpdated contentUpdated
    ) {
        try {

            CustomLogger.debugObject(CustomLoggerType.ENTER, contentUpdated);


            Content.repository().save(
                ContentManageService.getInstance().findByContentId(contentUpdated.getId())
                    .copyAllProperties(contentUpdated)
            );


            CustomLogger.debug(CustomLoggerType.EXIT);
            WebSocketEventHandler.getInstance().notifyEventsToSpecificUser(
                BookManageService.getInstance().findByBookId(
                    IndexManageService.getInstance().findByIndexId(contentUpdated.getIndexId()).getBookId()
                ).getCreaterId(), 
                "ContentUpdated", 
                String.format("{\"contentId\": %d}", contentUpdated.getId())
            );

        } catch (Exception e) {
            CustomLogger.errorObject(e, contentUpdated);
        }
    }
    
}
