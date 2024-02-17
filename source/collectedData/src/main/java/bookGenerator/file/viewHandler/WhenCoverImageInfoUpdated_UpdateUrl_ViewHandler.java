package bookGenerator.file.viewHandler;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import bookGenerator._global.config.kafka.KafkaProcessor;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;

import bookGenerator.file.domain.File;
import bookGenerator.file.domain.FileManageService;
import bookGenerator.file.event.CoverImageInfoUpdated;

@Service
public class WhenCoverImageInfoUpdated_UpdateUrl_ViewHandler {

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='CoverImageInfoUpdated'"
    )
    public void whenCoverImageInfoUpdated_UpdateUrl_ViewHandler(
        @Payload CoverImageInfoUpdated coverImageInfoUpdated
    ) {
        try {

            CustomLogger.debugObject(CustomLoggerType.ENTER, coverImageInfoUpdated);


            File.repository().save(
                FileManageService.getInstance().findByFileId(coverImageInfoUpdated.getId())
                    .copyAllProperties(coverImageInfoUpdated)
            );


            CustomLogger.debug(CustomLoggerType.EXIT);

        } catch (Exception e) {
            CustomLogger.errorObject(e, coverImageInfoUpdated);
        }
    }
    
}
