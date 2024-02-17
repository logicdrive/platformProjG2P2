package bookGenerator.content.viewHandler;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import bookGenerator._global.config.kafka.KafkaProcessor;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;

import bookGenerator.content.domain.Content;
import bookGenerator.content.domain.ContentManageService;
import bookGenerator.content.event.ContentDeletedByFail;

@Service
public class WhenContentDeletedByFail_DeleteContent_ViewHandler {

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='ContentDeletedByFail'"
    )
    public void whenContentDeletedByFail_DeleteContent_ViewHandler(
        @Payload ContentDeletedByFail contentDeletedByFail
    ) {
        try {

            CustomLogger.debugObject(CustomLoggerType.ENTER, contentDeletedByFail);


            Content.repository().delete(
                ContentManageService.getInstance().findByContentId(contentDeletedByFail.getId())
            );


            CustomLogger.debug(CustomLoggerType.EXIT);

        } catch (Exception e) {
            CustomLogger.errorObject(e, contentDeletedByFail);
        }
    }
    
}
