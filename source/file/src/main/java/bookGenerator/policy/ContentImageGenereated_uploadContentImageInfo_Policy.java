package bookGenerator.policy;

import javax.transaction.Transactional;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import bookGenerator._global.config.kafka.KafkaProcessor;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;
import bookGenerator._global.event.ContentImageGenereated;

@Service
@Transactional
public class ContentImageGenereated_uploadContentImageInfo_Policy {

    // ContentImageGenereated 이벤트 발생 관련 정책
    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='ContentImageGenereated'"
    )
    public void contentImageGenereated_uploadContentImageInfo_Policy(
        @Payload ContentImageGenereated contentImageGenereated
    ) {
        try
        {
            
            CustomLogger.debugObject(CustomLoggerType.ENTER_EXIT, "ContentImageGenereated", contentImageGenereated);

        } catch(Exception e) {
            CustomLogger.errorObject(e, "", contentImageGenereated);        
        }
    }

}
