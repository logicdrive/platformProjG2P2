package bookGenerator._global.event;

import lombok.NoArgsConstructor;
import bookGenerator._global.eventBase.EventNameAnnotation;
import bookGenerator._global.eventBase.UserEvent;
import bookGenerator.domain.User;

// 유저 이름이 업데이트 되었음을 알리기 위한 이벤트
@EventNameAnnotation(eventName="UserNameUpdated")
@NoArgsConstructor
public class UserNameUpdated extends UserEvent {
    public UserNameUpdated(User aggregate) {
        super(aggregate);
    }
}
