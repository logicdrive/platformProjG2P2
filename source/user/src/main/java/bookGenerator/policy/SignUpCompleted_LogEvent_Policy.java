package bookGenerator.policy;

import javax.transaction.Transactional;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import bookGenerator._global.config.kafka.KafkaProcessor;
import bookGenerator._global.event.SignUpCompleted;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;

@Service
@Transactional
public class SignUpCompleted_LogEvent_Policy {

    // SignUpCompleted 이벤트 발생 시 로그를 남기는 정책
    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='SignUpCompleted'"
    )
    public void signUpCompleted_LogEvent_Policy(
        @Payload SignUpCompleted signUpCompleted
    ) {
        try
        {
            
            CustomLogger.debugObject(CustomLoggerType.ENTER_EXIT, "LogEvent", signUpCompleted);

        } catch(Exception e) {
            CustomLogger.errorObject(e, "", signUpCompleted);        
        }
    }

}
