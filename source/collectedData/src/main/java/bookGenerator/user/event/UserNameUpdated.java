package bookGenerator.user.event;

import lombok.NoArgsConstructor;
import bookGenerator._global.eventBase.EventNameAnnotation;
import bookGenerator._global.eventBase.UserEvent;

// 유저 이름이 업데이트 되었음을 알리기 위한 이벤트
@NoArgsConstructor
@EventNameAnnotation(eventName="UserNameUpdated")
public class UserNameUpdated extends UserEvent {
}
