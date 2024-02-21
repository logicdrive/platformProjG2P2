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
import bookGenerator.content.event.ContentImageFileIdUpdated;
import bookGenerator.index.domain.IndexManageService;
import bookGenerator.webSocket.WebSocketEventHandler;

@Service
public class WhenContentImageFileIdUpdated_UpdateImageFileId_ViewHandler {

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='ContentImageFileIdUpdated'"
    )
    public void whenContentImageFileIdUpdated_UpdateImageFileId_ViewHandler(
        @Payload ContentImageFileIdUpdated contentImageFileIdUpdated
    ) {
        try {

            CustomLogger.debugObject(CustomLoggerType.ENTER, contentImageFileIdUpdated);


            Content.repository().save(
                ContentManageService.getInstance().findByContentId(contentImageFileIdUpdated.getId())
                    .copyAllProperties(contentImageFileIdUpdated)
            );


            CustomLogger.debug(CustomLoggerType.EXIT);
            WebSocketEventHandler.getInstance().notifyEventsToSpecificUser(
                BookManageService.getInstance().findByBookId(
                    IndexManageService.getInstance().findByIndexId(contentImageFileIdUpdated.getIndexId()).getBookId()
                ).getCreaterId(), 
                "ContentImageFileIdUpdated", 
                String.format("{\"contentId\": %d}", contentImageFileIdUpdated.getId())
            );

        } catch (Exception e) {
            CustomLogger.errorObject(e, contentImageFileIdUpdated);
        }
    }
    
}
