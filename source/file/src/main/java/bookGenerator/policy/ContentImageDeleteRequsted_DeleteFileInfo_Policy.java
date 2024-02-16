package bookGenerator.policy;

import javax.transaction.Transactional;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import bookGenerator._global.config.kafka.KafkaProcessor;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;
import bookGenerator._global.event.ContentImageDeleteRequsted;
import bookGenerator._global.event.FileInfoDeleted;

import bookGenerator.domain.File;
import bookGenerator.domain.FileManageService;

@Service
@Transactional
public class ContentImageDeleteRequsted_DeleteFileInfo_Policy {

    // ContentImageDeleteRequsted 이벤트 발생 관련 정책
    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='ContentImageDeleteRequsted'"
    )
    public void contentImageDeleteRequsted_DeleteFileInfo_Policy(
        @Payload ContentImageDeleteRequsted contentImageDeleteRequsted
    ) {
        try
        {

            CustomLogger.debugObject(CustomLoggerType.ENTER, contentImageDeleteRequsted);


            File fileToDelete = FileManageService.getInstance().findByIdOrThrow(contentImageDeleteRequsted.getImageFileId());
            File.repository().delete(fileToDelete);

            (new FileInfoDeleted(fileToDelete)).publish();


            CustomLogger.debug(CustomLoggerType.EXIT);

        } catch(Exception e) {
            CustomLogger.errorObject(e, "", contentImageDeleteRequsted);        
        }
    }

}
