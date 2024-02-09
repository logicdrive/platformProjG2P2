package bookGenerator._global.event;

import lombok.NoArgsConstructor;
import bookGenerator._global.eventBase.EventNameAnnotation;
import bookGenerator._global.eventBase.UserEvent;
import bookGenerator.domain.EntityTemplate;

// 회원가입 정보 등록이 완료됨을 알리기 위한 이벤트
@EventNameAnnotation(eventName="SignUpCompleted")
@NoArgsConstructor
public class SignUpCompleted extends UserEvent {
    public SignUpCompleted(EntityTemplate aggregate) {
        super(aggregate);
    }
}
