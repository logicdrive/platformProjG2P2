package bookGenerator.content.viewHandler;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import bookGenerator._global.config.kafka.KafkaProcessor;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;

import bookGenerator.content.domain.Content;
import bookGenerator.content.event.ContentImageGenerationRequested;

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

        } catch (Exception e) {
            CustomLogger.errorObject(e, contentImageGenerationRequested);
        }
    }
    
}
