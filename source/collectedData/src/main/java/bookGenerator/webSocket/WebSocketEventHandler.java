package bookGenerator.webSocket;

import java.io.IOException;
import java.util.HashMap;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import org.json.JSONObject;

import bookGenerator.BootApplication;

import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;

import lombok.Getter;
import lombok.ToString;


@ToString
@Getter
class WebSocketEventReqDto {
    Long userId;

    public WebSocketEventReqDto(TextMessage textMessage) {
        JSONObject jsonObject = new JSONObject(textMessage.getPayload());
        this.userId = jsonObject.getLong("userId");
    }
}

@ToString
class WebSocketEventResDto {
    String eventName;
    String value;

    public WebSocketEventResDto(String eventName, String value) {
        this.eventName = eventName;
        this.value = value;
    }

    public TextMessage jsonTextMessage() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("eventName", this.eventName);
        jsonObject.put("value", this.value);
        return new TextMessage(jsonObject.toString());
    }
}


@Component
public class WebSocketEventHandler extends TextWebSocketHandler {
    private final HashMap<Long, HashMap<String, WebSocketSession>> subscribes = new HashMap<>();

    public static WebSocketEventHandler getInstance() {
        return BootApplication.applicationContext.getBean(
            WebSocketEventHandler.class
        );
    }


    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        try {
            
            WebSocketEventReqDto webSocketEventReqDto = new WebSocketEventReqDto(message);
            CustomLogger.debugObject(CustomLoggerType.ENTER, webSocketEventReqDto);


            if(!this.subscribes.containsKey(webSocketEventReqDto.getUserId()))
                this.subscribes.put(webSocketEventReqDto.getUserId(), new HashMap<>());
            
            if(!this.subscribes.get(webSocketEventReqDto.getUserId()).containsKey(session.getId()))
                this.subscribes.get(webSocketEventReqDto.getUserId()).put(session.getId(), session);
                CustomLogger.debug(CustomLoggerType.EFFECT, "Session was added", String.format("{userId: %d, sessionId: %s}", webSocketEventReqDto.getUserId(), session.getId()));

        } catch (Exception e) {
            CustomLogger.error(e, "", String.format("{message: %s}", message.toString()));
        }
    }


    public void notifyEventsToAll(String eventName, String value) {
        try {

            CustomLogger.debug(CustomLoggerType.ENTER, "Try to notify events for all", String.format("{eventName: %s, value: %s}", eventName, value));


            for(HashMap<String, WebSocketSession> subscribedSessions : this.subscribes.values()) {
                for(String sessionId : subscribedSessions.keySet()) {
                    WebSocketSession session = subscribedSessions.get(sessionId);
                    if(!session.isOpen())
                    {
                        subscribedSessions.remove(sessionId);
                        CustomLogger.debug(CustomLoggerType.EFFECT, "Remove closed session", String.format("{sessionId: %s}", sessionId));
                        continue;
                    }


                    WebSocketEventResDto webSocketEventResDto = new WebSocketEventResDto(eventName, value);
                    CustomLogger.debug(CustomLoggerType.EFFECT, "Notify events for all", String.format("{webSocketEventResDto: %s}", webSocketEventResDto.toString()));

                    session.sendMessage(webSocketEventResDto.jsonTextMessage());
                }
            }

        } catch (IOException e) {
            CustomLogger.error(e, "", String.format("{eventName: %s, value: %s}", eventName, value));
        }
    }

    public void notifyEventsToSpecificUser(Long userId, String eventName, String value) {
        try {

            CustomLogger.debug(CustomLoggerType.ENTER, "Try to notify events for specific user", String.format("{userId: %s, eventName: %s, value: %s}", userId, eventName, value));


            if(!this.subscribes.containsKey(userId))
                return;
            
            for(String sessionId : this.subscribes.get(userId).keySet()) {
                WebSocketSession session = this.subscribes.get(userId).get(sessionId);
                if(!session.isOpen())
                {
                    this.subscribes.get(userId).remove(sessionId);
                    CustomLogger.debug(CustomLoggerType.EFFECT, "Remove closed session", String.format("{sessionId: %s}", sessionId));
                    continue;
                }
            
                WebSocketEventResDto webSocketEventResDto = new WebSocketEventResDto(eventName, value);
                CustomLogger.debug(CustomLoggerType.EFFECT, "Notify events for specific user", String.format("{webSocketEventResDto: %s}", webSocketEventResDto.toString()));

                session.sendMessage(webSocketEventResDto.jsonTextMessage());
            }

        } catch (IOException e) {
            CustomLogger.error(e, "", String.format("{userId: %s, eventName: %s, value: %s}", userId, eventName, value));
        }
    }
}
