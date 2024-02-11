package bookGenerator.policy;

import javax.transaction.Transactional;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import bookGenerator._global.config.kafka.KafkaProcessor;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;
import bookGenerator._global.event.EmptyBookCreated;

@Service
@Transactional
public class EmptyBookCreated_createEmptyCoverImageInfo_Policy {

    // EmptyBookCreated 이벤트 발생 관련 정책
    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='EmptyBookCreated'"
    )
    public void emptyBookCreated_createEmptyCoverImageInfo_Policy(
        @Payload EmptyBookCreated emptyBookCreated
    ) {
        try
        {

            CustomLogger.debugObject(CustomLoggerType.ENTER, emptyBookCreated);

            

            CustomLogger.debug(CustomLoggerType.EXIT);

        } catch(Exception e) {
            CustomLogger.errorObject(e, "", emptyBookCreated);        
        }
    }

}
