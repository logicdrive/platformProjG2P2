package bookGenerator.tag.viewHandler;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import bookGenerator._global.config.kafka.KafkaProcessor;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;

import bookGenerator.tag.domain.Tag;
import bookGenerator.tag.event.TagCreated;

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

        } catch (Exception e) {
            CustomLogger.errorObject(e, tagCreated);
        }
    }
    
}
