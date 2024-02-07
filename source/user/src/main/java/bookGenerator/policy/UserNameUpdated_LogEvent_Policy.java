package bookGenerator.policy;

import javax.transaction.Transactional;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import bookGenerator._global.config.kafka.KafkaProcessor;
import bookGenerator._global.event.UserNameUpdated;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;

@Service
@Transactional
public class UserNameUpdated_LogEvent_Policy {

    // UserNameUpdated 이벤트 발생 시 로그를 남기는 정책
    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='UserNameUpdated'"
    )
    public void userNameUpdated_LogEvent_Policy(
        @Payload UserNameUpdated userNameUpdated
    ) {
        try
        {
            
            CustomLogger.debugObject(CustomLoggerType.ENTER_EXIT, "LogEvent", userNameUpdated);

        } catch(Exception e) {
            CustomLogger.errorObject(e, "", userNameUpdated);        
        }
    }

}
