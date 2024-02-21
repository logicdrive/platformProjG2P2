package bookGenerator.content.viewHandler;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import bookGenerator._global.config.kafka.KafkaProcessor;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;
import bookGenerator.book.domain.BookManageService;
import bookGenerator.content.domain.Content;
import bookGenerator.content.event.ContentImageGenerationRequested;
import bookGenerator.index.domain.IndexManageService;
import bookGenerator.webSocket.WebSocketEventHandler;

@Service
public class WhenContentImageGenerationRequested_CreateContent_ViewHandler {

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='ContentImageGenerationRequested'"
    )
    public void whenContentImageGenerationRequested_CreateContent_ViewHandler(
        @Payload ContentImageGenerationRequested contentImageGenerationRequested
    ) {
        try {

            CustomLogger.debugObject(CustomLoggerType.ENTER, contentImageGenerationRequested);


            Content.repository().save(
                Content.createWithObject(contentImageGenerationRequested)
            );


            CustomLogger.debug(CustomLoggerType.EXIT);
            WebSocketEventHandler.getInstance().notifyEventsToSpecificUser(
                BookManageService.getInstance().findByBookId(
                    IndexManageService.getInstance().findByIndexId(contentImageGenerationRequested.getIndexId()).getBookId()
                ).getCreaterId(), 
                "ContentImageGenerationRequested", 
                String.format("{\"contentId\": %d}", contentImageGenerationRequested.getId())
            );

        } catch (Exception e) {
            CustomLogger.errorObject(e, contentImageGenerationRequested);
        }
    }
    
}
