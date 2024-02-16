package bookGenerator.policy;

import javax.transaction.Transactional;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import bookGenerator._global.config.kafka.KafkaProcessor;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;
import bookGenerator._global.event.ExistingCoverImageDeleted;
import bookGenerator._global.event.ExistingCoverImageDeletingRequested;
import bookGenerator._global.event.ExistingCoverImageDeleteFailed;
import bookGenerator._global.externalSystemProxy.s3.removeFile.ExternalSystemProxy_RemoveFile;
import bookGenerator._global.externalSystemProxy.s3.removeFile.RemoveFileReqDto;

@Service
@Transactional
public class ExistingCoverImageDeletingRequested_deleteExistingCoverImage_Policy {

    // ExistingCoverImageDeletingRequested 이벤트 발생 관련 정책
    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='ExistingCoverImageDeletingRequested'"
    )
    public void existingCoverImageDeletingRequested_deleteExistingCoverImage_Policy(
        @Payload ExistingCoverImageDeletingRequested existingCoverImageDeletingRequested
    ) {
        try
        {

            CustomLogger.debugObject(CustomLoggerType.ENTER, existingCoverImageDeletingRequested);


            RemoveFileReqDto reqDto = new RemoveFileReqDto(existingCoverImageDeletingRequested.getUrl());
            ExternalSystemProxy_RemoveFile.getInstance().externalSystemProxy_RemoveFile(reqDto);

            (new ExistingCoverImageDeleted(existingCoverImageDeletingRequested.getId())).publish();


            CustomLogger.debug(CustomLoggerType.EXIT);

        } catch(Exception e) {
            CustomLogger.errorObject(e, "", existingCoverImageDeletingRequested);
            
            (new ExistingCoverImageDeleteFailed(existingCoverImageDeletingRequested.getId())).publish();
        }
    }

}
