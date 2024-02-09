package bookGenerator.policy;

import javax.transaction.Transactional;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import bookGenerator._global.config.kafka.KafkaProcessor;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;
import bookGenerator._global.event.TagGernerated;

@Service
@Transactional
public class TagGernerated_createTag_Policy {

    // TagGernerated 이벤트 발생 관련 정책
    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='TagGernerated'"
    )
    public void tagGernerated_createTag_Policy(
        @Payload TagGernerated tagGernerated
    ) {
        try
        {
            
            CustomLogger.debugObject(CustomLoggerType.ENTER_EXIT, "TagGernerated", tagGernerated);

        } catch(Exception e) {
            CustomLogger.errorObject(e, "", tagGernerated);        
        }
    }

}
