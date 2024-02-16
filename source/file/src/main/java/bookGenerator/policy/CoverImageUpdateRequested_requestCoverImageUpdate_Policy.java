package bookGenerator.policy;

import javax.transaction.Transactional;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import bookGenerator._global.config.kafka.KafkaProcessor;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;
import bookGenerator._global.event.CoverImageUpdateRequested;
import bookGenerator._global.event.CoverImageUpdateRequestedByFile;
import bookGenerator._global.event.ExistingCoverImageDeletingRequested;

import bookGenerator.domain.File;
import bookGenerator.domain.FileManageService;

@Service
@Transactional
public class CoverImageUpdateRequested_requestCoverImageUpdate_Policy {

    // Book에 대한 Cover Image 업데이트가 요청되었을 경우, 기존의 이미지 제거 및 Cover Image 업데이트 이벤트를 발생시키기 위해서
    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='CoverImageUpdateRequested'"
    )
    public void coverImageUpdateRequested_requestCoverImageUpdate_Policy(
        @Payload CoverImageUpdateRequested coverImageUpdateRequested
    ) {
        try
        {

            CustomLogger.debugObject(CustomLoggerType.ENTER, coverImageUpdateRequested);


            File searchedFile = FileManageService.getInstance().findByIdOrThrow(coverImageUpdateRequested.getCoverImageFileId());

            if(searchedFile.getUrl() != null) (new ExistingCoverImageDeletingRequested(searchedFile)).publish();
            (new CoverImageUpdateRequestedByFile(searchedFile, coverImageUpdateRequested.getImageUrl())).publish();


            CustomLogger.debug(CustomLoggerType.EXIT);

        } catch(Exception e) {
            CustomLogger.errorObject(e, "", coverImageUpdateRequested);        
        }
    }

}
