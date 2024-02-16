package bookGenerator.policy;

import javax.transaction.Transactional;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import bookGenerator._global.config.kafka.KafkaProcessor;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;
import bookGenerator._global.event.ContentDeletedByFail;
import bookGenerator._global.event.ContentImageGenerationFailed;

import bookGenerator.domain.Content;
import bookGenerator.domain.ContentManageService;

@Service
@Transactional
public class ContentImageGenerationFailed_deleteContentByFail_Policy {

    // 이미지 생성에 실패시에 관련 컨텐츠를 삭제하고, 이벤트를 발생시키는 정책
    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='ContentImageGenerationFailed'"
    )
    public void contentImageGenerationFailed_deleteContentByFail_Policy(
        @Payload ContentImageGenerationFailed contentImageGenerationFailed
    ) {
        try
        {
            
            CustomLogger.debugObject(CustomLoggerType.ENTER, contentImageGenerationFailed);


            Content contentToDelete = ContentManageService.getInstance().findByIdOrThrow(contentImageGenerationFailed.getContentId());
            Content.repository().delete(contentToDelete);

            (new ContentDeletedByFail(contentToDelete)).publish();

            
            CustomLogger.debug(CustomLoggerType.EXIT);

        } catch(Exception e) {
            CustomLogger.errorObject(e, "", contentImageGenerationFailed);        
        }
    }

}
