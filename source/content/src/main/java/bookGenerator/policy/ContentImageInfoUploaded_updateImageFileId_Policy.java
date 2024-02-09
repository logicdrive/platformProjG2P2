package bookGenerator.policy;

import javax.transaction.Transactional;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import bookGenerator._global.config.kafka.KafkaProcessor;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;
import bookGenerator._global.event.ContentImageInfoUploaded;

@Service
@Transactional
public class ContentImageInfoUploaded_updateImageFileId_Policy {

    // ContentImageInfoUploaded 이벤트 발생 관련 정책
    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='ContentImageInfoUploaded'"
    )
    public void contentImageInfoUploaded_updateImageFileId_Policy(
        @Payload ContentImageInfoUploaded contentImageInfoUploaded
    ) {
        try
        {
            
            CustomLogger.debugObject(CustomLoggerType.ENTER_EXIT, "ContentImageInfoUploaded", contentImageInfoUploaded);

        } catch(Exception e) {
            CustomLogger.errorObject(e, "", contentImageInfoUploaded);        
        }
    }

}
