package bookGenerator.policy;

import javax.transaction.Transactional;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import bookGenerator._global.config.kafka.KafkaProcessor;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;
import bookGenerator._global.event.CoverImageUpdateRequestedByFile;
import bookGenerator._global.event.CoverImageUploadFailed;
import bookGenerator._global.event.CoverImageUploaded;
import bookGenerator._global.externalSystemProxy.s3.uploadFile.ExternalSystemProxy_UploadFile;
import bookGenerator._global.externalSystemProxy.s3.uploadFile.UploadFileReqDto;
import bookGenerator._global.externalSystemProxy.s3.uploadFile.UploadFileResDto;

@Service
@Transactional
public class CoverImageUpdateRequestedByFile_uploadNewCoverImage_Policy {

    // CoverImageUpdateRequestedByFile 이벤트 발생 관련 정책
    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='CoverImageUpdateRequestedByFile'"
    )
    public void coverImageUpdateRequestedByFile_uploadNewCoverImage_Policy(
        @Payload CoverImageUpdateRequestedByFile coverImageUpdateRequestedByFile
    ) {
        try
        {

            CustomLogger.debugObject(CustomLoggerType.ENTER, coverImageUpdateRequestedByFile);


            UploadFileReqDto reqDto = new UploadFileReqDto(coverImageUpdateRequestedByFile.getImageUrl());
            UploadFileResDto resDto = ExternalSystemProxy_UploadFile.getInstance().externalSystemProxy_UploadFile(reqDto);

            (new CoverImageUploaded(coverImageUpdateRequestedByFile.getId(), resDto.getFileUrl())).publish();

            
            CustomLogger.debug(CustomLoggerType.EXIT);

        } catch(Exception e) {
            CustomLogger.errorObject(e, "", coverImageUpdateRequestedByFile);
            
            (new CoverImageUploadFailed(coverImageUpdateRequestedByFile.getId())).publish();
        }
    }

}
