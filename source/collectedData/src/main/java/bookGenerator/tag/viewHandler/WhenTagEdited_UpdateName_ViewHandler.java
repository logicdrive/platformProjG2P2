package bookGenerator.tag.viewHandler;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import bookGenerator._global.config.kafka.KafkaProcessor;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;

import bookGenerator.tag.domain.Tag;
import bookGenerator.tag.domain.TagManageService;
import bookGenerator.tag.event.TagEdited;

@Service
public class WhenTagEdited_UpdateName_ViewHandler {

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='TagEdited'"
    )
    public void whenTagEdited_UpdateName_ViewHandler(
        @Payload TagEdited tagEdited
    ) {
        try {

            CustomLogger.debugObject(CustomLoggerType.ENTER, tagEdited);


            Tag.repository().save(
                TagManageService.getInstance().findByTagId(tagEdited.getId())
                    .copyAllProperties(tagEdited)
            );


            CustomLogger.debug(CustomLoggerType.EXIT);

        } catch (Exception e) {
            CustomLogger.errorObject(e, tagEdited);
        }
    }
    
}
