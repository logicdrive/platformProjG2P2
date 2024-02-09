package bookGenerator.policy;

import javax.transaction.Transactional;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import bookGenerator._global.config.kafka.KafkaProcessor;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;
import bookGenerator._global.event.FileInfoDeleted;

@Service
@Transactional
public class FileInfoDeleted_deleteExistingCoverImage_Policy {

    // FileInfoDeleted 이벤트 발생 관련 정책
    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='FileInfoDeleted'"
    )
    public void fileInfoDeleted_deleteExistingCoverImage_Policy(
        @Payload FileInfoDeleted fileInfoDeleted
    ) {
        try
        {
            
            CustomLogger.debugObject(CustomLoggerType.ENTER_EXIT, "FileInfoDeleted", fileInfoDeleted);

        } catch(Exception e) {
            CustomLogger.errorObject(e, "", fileInfoDeleted);        
        }
    }

}
