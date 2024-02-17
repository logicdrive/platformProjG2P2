package bookGenerator.user.viewHandler;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import bookGenerator._global.config.kafka.KafkaProcessor;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;
import bookGenerator.user.domain.User;
import bookGenerator.user.event.SignUpCompleted;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WhenSignUpCompleted_CreateUser_ViewHandler {

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='SignUpCompleted'"
    )
    public void whenSignUpCompleted_CreateUser_ViewHandler(
        @Payload SignUpCompleted signUpCompleted
    ) {
        try {

            CustomLogger.debugObject(CustomLoggerType.ENTER, signUpCompleted);


            User.repository().save(
                User.createWithObject(signUpCompleted)
            );


            CustomLogger.debug(CustomLoggerType.EXIT);

        } catch (Exception e) {
            CustomLogger.errorObject(e, signUpCompleted);
        }
    }
    
}
