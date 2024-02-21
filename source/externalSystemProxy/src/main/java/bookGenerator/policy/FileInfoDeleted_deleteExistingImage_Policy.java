package bookGenerator.policy;

import javax.transaction.Transactional;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import bookGenerator._global.config.kafka.KafkaProcessor;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;
import bookGenerator._global.event.ExistingCoverImageDeleteFailed;
import bookGenerator._global.event.ExistingCoverImageDeleted;
import bookGenerator._global.event.FileInfoDeleted;
import bookGenerator._global.externalSystemProxy.s3.removeFile.ExternalSystemProxy_RemoveFile;
import bookGenerator._global.externalSystemProxy.s3.removeFile.RemoveFileReqDto;

@Service
@Transactional
public class FileInfoDeleted_deleteExistingImage_Policy {

    // FileInfoDeleted 이벤트 발생 관련 정책
    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='FileInfoDeleted'"
    )
    public void fileInfoDeleted_deleteExistingImage_Policy(
        @Payload FileInfoDeleted fileInfoDeleted
    ) {
        try
        {

            CustomLogger.debugObject(CustomLoggerType.ENTER, fileInfoDeleted);
            if(fileInfoDeleted.getUrl() == null || fileInfoDeleted.getUrl().isEmpty()) {
                CustomLogger.debug(CustomLoggerType.EXIT);
                return;
            }


            RemoveFileReqDto reqDto = new RemoveFileReqDto(fileInfoDeleted.getUrl());
            ExternalSystemProxy_RemoveFile.getInstance().externalSystemProxy_RemoveFile(reqDto);

            (new ExistingCoverImageDeleted(fileInfoDeleted.getId())).publish();
            

            CustomLogger.debug(CustomLoggerType.EXIT);

        } catch(Exception e) {
            CustomLogger.errorObject(e, "", fileInfoDeleted);    
            
            (new ExistingCoverImageDeleteFailed(fileInfoDeleted.getId())).publish();
        }
    }

}
