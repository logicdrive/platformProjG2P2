package bookGenerator.file.viewHandler;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import bookGenerator._global.config.kafka.KafkaProcessor;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;

import bookGenerator.file.domain.File;
import bookGenerator.file.event.EmptyCoverImageInfoCreated;

@Service
public class WhenEmptyCoverImageInfoCreated_CreateFile_ViewHandler {

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='EmptyCoverImageInfoCreated'"
    )
    public void whenEmptyCoverImageInfoCreated_CreateFile_ViewHandler(
        @Payload EmptyCoverImageInfoCreated emptyCoverImageInfoCreated
    ) {
        try {

            CustomLogger.debugObject(CustomLoggerType.ENTER, emptyCoverImageInfoCreated);


            File.repository().save(
                File.createWithObject(emptyCoverImageInfoCreated)
            );


            CustomLogger.debug(CustomLoggerType.EXIT);

        } catch (Exception e) {
            CustomLogger.errorObject(e, emptyCoverImageInfoCreated);
        }
    }
    
}
