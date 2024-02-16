package bookGenerator.policy;

import javax.transaction.Transactional;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import bookGenerator._global.config.kafka.KafkaProcessor;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;
import bookGenerator._global.event.ContentImageDeleteRequsted;
import bookGenerator._global.event.IndexDeleted;

import bookGenerator.domain.Content;
import bookGenerator.domain.ContentManageService;

@Service
@Transactional
public class IndexDeleted_deleteContent_Policy {

    // Index가 삭제되었을 경우, 연관된 Content도 함께 삭제시키기 위해서
    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='IndexDeleted'"
    )
    public void indexDeleted_deleteContent_Policy(
        @Payload IndexDeleted indexDeleted
    ) {
        try
        {
            
            CustomLogger.debugObject(CustomLoggerType.ENTER, indexDeleted);


            Content contentToDelete = ContentManageService.getInstance().findByIndexIdOrThrow(indexDeleted.getId());
            Content.repository().delete(contentToDelete);

            (new ContentImageDeleteRequsted(contentToDelete)).publish();


            CustomLogger.debug(CustomLoggerType.EXIT);

        } catch(Exception e) {
            CustomLogger.errorObject(e, "", indexDeleted);        
        }
    }

}
