package bookGenerator.policy;

import javax.transaction.Transactional;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import bookGenerator._global.config.kafka.KafkaProcessor;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;
import bookGenerator._global.event.ContentGenerationFailed;

@Service
@Transactional
public class ContentGenerationFailed_deleteContentByFail_Policy {

    // 컨텐츠 생성에 실패시에 관련 컨텐츠를 삭제하고, 이벤트를 발생시키는 정책
    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='ContentGenerationFailed'"
    )
    public void contentGenerationFailed_deleteContentByFail_Policy(
        @Payload ContentGenerationFailed contentGenerationFailed
    ) {
        try
        {
            
            CustomLogger.debugObject(CustomLoggerType.ENTER, contentGenerationFailed);

            // [1] contentGenerationFailed.contentId에 해당하는 컨텐츠를 삭제한다.

            // [2] 삭제된 Content에 대한 ContentDeletedByFail 이벤트를 발생시킨다.

            CustomLogger.debug(CustomLoggerType.EXIT);

        } catch(Exception e) {
            CustomLogger.errorObject(e, "", contentGenerationFailed);        
        }
    }

}
