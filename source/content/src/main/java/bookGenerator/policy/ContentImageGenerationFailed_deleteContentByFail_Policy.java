package bookGenerator.policy;

import javax.transaction.Transactional;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import bookGenerator._global.config.kafka.KafkaProcessor;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;
import bookGenerator.domain.Content;
import bookGenerator.domain.ContentManageService;
import bookGenerator._global.event.ContentDeletedByFail;
import bookGenerator._global.event.ContentmageGenerationFailed;

@Service
@Transactional
public class ContentImageGenerationFailed_deleteContentByFail_Policy {

    // 이미지 생성에 실패시에 관련 컨텐츠를 삭제하고, 이벤트를 발생시키는 정책
    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='ContentmageGenerationFailed'"
    )
    public void contentImageGenerationFailed_deleteContentByFail_Policy(
        @Payload ContentmageGenerationFailed contentmageGenerationFailed
    ) {
        try
        {
            
            CustomLogger.debugObject(CustomLoggerType.ENTER, contentmageGenerationFailed);

            // [1] contentmageGenerationFailed.contentId에 해당하는 컨텐츠를 삭제한다.
            Content contentToDelete = ContentManageService.getInstance().findByIdOrThrow(contentmageGenerationFailed.getContentId());
            Content.repository().delete(contentToDelete);
            
            // [2] 삭제된 Content에 대한 ContentDeletedByFail 이벤트를 발생시킨다.
            (new ContentDeletedByFail(contentToDelete)).publish();
            
            CustomLogger.debug(CustomLoggerType.EXIT);

        } catch(Exception e) {
            CustomLogger.errorObject(e, "", contentmageGenerationFailed);        
        }
    }

}
