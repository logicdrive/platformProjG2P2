package bookGenerator.tag.viewHandler;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import bookGenerator._global.config.kafka.KafkaProcessor;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;
import bookGenerator.book.domain.BookManageService;
import bookGenerator.tag.domain.Tag;
import bookGenerator.tag.event.TagCreated;
import bookGenerator.webSocket.WebSocketEventHandler;

@Service
public class WhenTagCreated_CreateTag_ViewHandler {

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='TagCreated'"
    )
    public void whenTagCreated_CreateTag_ViewHandler(
        @Payload TagCreated tagCreated
    ) {
        try {

            CustomLogger.debugObject(CustomLoggerType.ENTER, tagCreated);


            Tag.repository().save(
                Tag.createWithObject(tagCreated)
            );


            CustomLogger.debug(CustomLoggerType.EXIT);
            WebSocketEventHandler.getInstance().notifyEventsToSpecificUser(
                BookManageService.getInstance().findByBookId(tagCreated.getBookId()).getCreaterId(), 
                "TagCreated", 
                String.format("{\"tagId\": %d}", tagCreated.getId())
            );

        } catch (Exception e) {
            CustomLogger.errorObject(e, tagCreated);
        }
    }
    
}
