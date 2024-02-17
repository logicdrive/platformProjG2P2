package bookGenerator.file.viewHandler;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import bookGenerator._global.config.kafka.KafkaProcessor;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;

import bookGenerator.file.domain.File;
import bookGenerator.file.domain.FileManageService;
import bookGenerator.file.event.FileInfoDeleted;

@Service
public class WhenFileInfoDeleted_DeleteFile_ViewHandler {

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='FileInfoDeleted'"
    )
    public void whenFileInfoDeleted_DeleteFile_ViewHandler(
        @Payload FileInfoDeleted fileInfoDeleted
    ) {
        try {

            CustomLogger.debugObject(CustomLoggerType.ENTER, fileInfoDeleted);


            File.repository().delete(
                FileManageService.getInstance().findByFileId(fileInfoDeleted.getId())
            );


            CustomLogger.debug(CustomLoggerType.EXIT);

        } catch (Exception e) {
            CustomLogger.errorObject(e, fileInfoDeleted);
        }
    }
    
}
