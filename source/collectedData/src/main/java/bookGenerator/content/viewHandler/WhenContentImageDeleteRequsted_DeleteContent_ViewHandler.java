package bookGenerator.content.viewHandler;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import bookGenerator._global.config.kafka.KafkaProcessor;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;

import bookGenerator.content.domain.Content;
import bookGenerator.content.domain.ContentManageService;
import bookGenerator.content.event.ContentImageDeleteRequsted;

@Service
public class WhenContentImageDeleteRequsted_DeleteContent_ViewHandler {

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='ContentImageDeleteRequsted'"
    )
    public void whenContentImageDeleteRequsted_DeleteContent_ViewHandler(
        @Payload ContentImageDeleteRequsted contentImageDeleteRequsted
    ) {
        try {

            CustomLogger.debugObject(CustomLoggerType.ENTER, contentImageDeleteRequsted);


            Content.repository().delete(
                ContentManageService.getInstance().findByContentId(contentImageDeleteRequsted.getId())
            );


            CustomLogger.debug(CustomLoggerType.EXIT);

        } catch (Exception e) {
            CustomLogger.errorObject(e, contentImageDeleteRequsted);
        }
    }
    
}
