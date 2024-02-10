package bookGenerator.policy;

import javax.transaction.Transactional;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import bookGenerator._global.config.kafka.KafkaProcessor;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;
import bookGenerator._global.event.ContentGenerated;

@Service
@Transactional
public class ContentGenerated_updateContent_Policy {

    // ContentGenerated 이벤트 발생 관련 정책
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


            CustomLogger.debug(CustomLoggerType.EXIT);

        } catch(Exception e) {
            CustomLogger.errorObject(e, "", contentGenerated);        
        }
    }

}
