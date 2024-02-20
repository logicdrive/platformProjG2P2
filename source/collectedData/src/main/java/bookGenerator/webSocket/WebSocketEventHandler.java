package bookGenerator.webSocket;

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

        } catch (Exception e) {
            CustomLogger.error(e, "", String.format("{message: %s}", message.toString()));
        }
    }
}
