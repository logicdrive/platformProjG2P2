package bookGenerator.policy;

import javax.transaction.Transactional;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import bookGenerator._global.config.kafka.KafkaProcessor;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;
import bookGenerator._global.event.CoverImageUpdateRequested;

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

            // [1] coverImageUpdateRequested.coverImageFileId로 File을 찾음

            // [2] File을 이용해서 ExistingCoverImageDeletingRequested 이벤트를 발생시킴

            // [3] File을 이용해서 CoverImageUpdateRequestedByFile 이벤트를 발생시킴

            CustomLogger.debug(CustomLoggerType.EXIT);

        } catch(Exception e) {
            CustomLogger.errorObject(e, "", coverImageUpdateRequested);        
        }
    }

}
