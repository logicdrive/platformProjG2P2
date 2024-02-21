package bookGenerator.tag.viewHandler;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import bookGenerator._global.config.kafka.KafkaProcessor;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;
import bookGenerator.book.domain.BookManageService;
import bookGenerator.tag.domain.Tag;
import bookGenerator.tag.domain.TagManageService;
import bookGenerator.tag.event.TagDeleted;
import bookGenerator.webSocket.WebSocketEventHandler;

@Service
public class WhenTagDeleted_DeleteTag_ViewHandler {

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='TagDeleted'"
    )
    public void whenTagDeleted_DeleteTag_ViewHandler(
        @Payload TagDeleted tagDeleted
    ) {
        try {

            CustomLogger.debugObject(CustomLoggerType.ENTER, tagDeleted);


            Tag.repository().delete(
                TagManageService.getInstance().findByTagId(tagDeleted.getId())
            );


            CustomLogger.debug(CustomLoggerType.EXIT);
            try {

                WebSocketEventHandler.getInstance().notifyEventsToSpecificUser(
                    BookManageService.getInstance().findByBookId(tagDeleted.getBookId()).getCreaterId(), 
                    "tagDeleted", 
                    String.format("{\"tagId\": %d}", tagDeleted.getId())
                );

            } catch (Exception e) {
                CustomLogger.debug(CustomLoggerType.EFFECT, e.getMessage());
            }

        } catch (Exception e) {
            CustomLogger.errorObject(e, tagDeleted);
        }
    }
    
}
