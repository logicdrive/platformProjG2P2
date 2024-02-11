package bookGenerator.policy;

import javax.transaction.Transactional;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import bookGenerator._global.config.kafka.KafkaProcessor;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;
import bookGenerator._global.event.IndexDeleted;

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

            // [1] indexDeleted.id를 이용해여 Content를 찾아서 삭제한다.
            // [!] ContentManageService 를 활용한다.

            // [2] ContentImageDeleteRequsted 이벤트를 삭제한 Content를 기반으로 발생시킨다.

            CustomLogger.debug(CustomLoggerType.EXIT);

        } catch(Exception e) {
            CustomLogger.errorObject(e, "", indexDeleted);        
        }
    }

}
