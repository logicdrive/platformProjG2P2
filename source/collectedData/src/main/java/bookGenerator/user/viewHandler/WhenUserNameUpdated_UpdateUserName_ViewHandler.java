package bookGenerator.user.viewHandler;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import bookGenerator._global.config.kafka.KafkaProcessor;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;
import bookGenerator.user.domain.User;
import bookGenerator.user.domain.UserManageService;
import bookGenerator.user.event.UserNameUpdated;
import bookGenerator.webSocket.WebSocketEventHandler;

@Service
public class WhenUserNameUpdated_UpdateUserName_ViewHandler {

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='UserNameUpdated'"
    )
    public void whenUserNameUpdated_UpdateUserName_ViewHandler(
        @Payload UserNameUpdated userNameUpdated
    ) {
        try {

            CustomLogger.debugObject(CustomLoggerType.ENTER, userNameUpdated);


            User.repository().save(
                UserManageService.getInstance().findByUserIdOrThrow(userNameUpdated.getId())
                    .copyAllProperties(userNameUpdated)
            );


            CustomLogger.debug(CustomLoggerType.EXIT);
            WebSocketEventHandler.getInstance().notifyEventsToSpecificUser(
                userNameUpdated.getId(), "UserNameUpdated", userNameUpdated.getName()
            );

        } catch (Exception e) {
            CustomLogger.errorObject(e, userNameUpdated);
        }
    }
    
}
