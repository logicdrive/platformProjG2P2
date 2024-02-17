package bookGenerator.file.viewHandler;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import bookGenerator._global.config.kafka.KafkaProcessor;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;

import bookGenerator.file.domain.File;
import bookGenerator.file.event.ContentImageInfoUploaded;

@Service
public class WhenContentImageInfoUploaded_CreateFile_ViewHandler {

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='ContentImageInfoUploaded'"
    )
    public void whenContentImageInfoUploaded_CreateFile_ViewHandler(
        @Payload ContentImageInfoUploaded contentImageInfoUploaded
    ) {
        try {

            CustomLogger.debugObject(CustomLoggerType.ENTER, contentImageInfoUploaded);


            File.repository().save(
                File.createWithObject(contentImageInfoUploaded)
            );


            CustomLogger.debug(CustomLoggerType.EXIT);

        } catch (Exception e) {
            CustomLogger.errorObject(e, contentImageInfoUploaded);
        }
    }
    
}
