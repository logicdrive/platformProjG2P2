package bookGenerator.policy;

import javax.transaction.Transactional;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import bookGenerator._global.config.kafka.KafkaProcessor;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;
import bookGenerator._global.event.ContentGenerated;
import bookGenerator._global.event.ContentUpdated;

import bookGenerator.domain.Content;
import bookGenerator.domain.ContentManageService;

@Service
@Transactional
public class ContentGenerated_updateContent_Policy {

    // 컨텐츠 생성을 요청한 파일에 대한 정보가 이벤트로 발생되었을 때, 관련 content를 업데이트하기 위해서
    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='ContentGenerated'"
    )
    public void contentGenerated_updateContent_Policy(
        @Payload ContentGenerated contentGenerated
    ) {
        try
        {
            
            CustomLogger.debugObject(CustomLoggerType.ENTER, contentGenerated);

 
            Content contentToUpdate = ContentManageService.getInstance().findByIdOrThrow(contentGenerated.getContentId());
            contentToUpdate.setContent(contentGenerated.getContent());
            Content.repository().save(contentToUpdate);

            (new ContentUpdated(contentToUpdate)).publish();

            
            CustomLogger.debug(CustomLoggerType.EXIT);

        } catch(Exception e) {
            CustomLogger.errorObject(e, "", contentGenerated);        
        }
    }

}
