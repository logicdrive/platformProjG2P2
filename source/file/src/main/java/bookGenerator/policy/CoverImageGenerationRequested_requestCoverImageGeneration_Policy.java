package bookGenerator.policy;

import javax.transaction.Transactional;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import bookGenerator._global.config.kafka.KafkaProcessor;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;
import bookGenerator.domain.File;
import bookGenerator.domain.FileManageService;
import bookGenerator._global.event.CoverImageGenerationRequested;
import bookGenerator._global.event.CoverImageGenerationRequestedByFile;
import bookGenerator._global.event.ExistingCoverImageDeletingRequested;

@Service
@Transactional
public class CoverImageGenerationRequested_requestCoverImageGeneration_Policy {

    // Book에 대한 Cover Image 생성이 요청되었을 경우, 기존의 이미지 제거 및 새로운 생성 이벤트를 발생시키기 위해서
    @StreamListener(value = KafkaProcessor.INPUT, condition = "headers['type']=='CoverImageGenerationRequested'")
    public void coverImageGenerationRequested_requestCoverImageGeneration_Policy(
            @Payload CoverImageGenerationRequested coverImageGenerationRequested) {
        try {

            if (coverImageGenerationRequested == null) {
                throw new IllegalArgumentException("coverImageGenerationRequested is null");
            }

            CustomLogger.debugObject(CustomLoggerType.ENTER, coverImageGenerationRequested);

            // [1] coverImageGenerationRequested.coverImageFileId로 File을 찾음

            File file = FileManageService.getInstance()
                    .findByIdOrThrow(coverImageGenerationRequested.getCoverImageFileId());

            // [2] File을 이용해서 ExistingCoverImageDeletingRequested 이벤트를 발생시킴
            ExistingCoverImageDeletingRequested deletingRequestedEvent = new ExistingCoverImageDeletingRequested(file);
            deletingRequestedEvent.publish();

            // [3] File을 이용해서 CoverImageGenerationRequestedByFile 이벤트를 발생시킴
            CoverImageGenerationRequestedByFile generationRequestedEvent = new CoverImageGenerationRequestedByFile(
                    file);
            generationRequestedEvent.publish();

            CustomLogger.debug(CustomLoggerType.EXIT);

        } catch (Exception e) {
            CustomLogger.errorObject(e, "", coverImageGenerationRequested);
        }
    }

}
