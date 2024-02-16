package bookGenerator.policy;

import javax.transaction.Transactional;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import bookGenerator._global.config.kafka.KafkaProcessor;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;
import bookGenerator.domain.File;
import bookGenerator.domain.FileManageService;
import bookGenerator._global.event.ContentImageDeleteRequsted;
import bookGenerator._global.event.FileInfoDeleted;

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

            // [1] contentImageDeleteRequsted.imageFileId에 해당하는 File의 Id를 조회한다.
            File file = FileManageService.getInstance().findByIdOrThrow(contentImageDeleteRequsted.getImageFileId());

            // [2] 조회된 File을 삭제한다.
            File.repository().delete(file);

            // [3] 삭제된 File로 FileInfoDeleted 이벤트를 발생시킨다.
            (new FileInfoDeleted(file)).publish();
            
            CustomLogger.debug(CustomLoggerType.EXIT);

        } catch(Exception e) {
            CustomLogger.errorObject(e, "", contentImageDeleteRequsted);        
        }
    }

}
