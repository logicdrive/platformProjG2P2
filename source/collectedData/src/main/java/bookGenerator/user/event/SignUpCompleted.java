package bookGenerator.user.event;

import lombok.NoArgsConstructor;
import bookGenerator._global.eventBase.EventNameAnnotation;
import bookGenerator._global.eventBase.UserEvent;

// 회원가입 정보 등록이 완료됨을 알리기 위한 이벤트
@NoArgsConstructor
@EventNameAnnotation(eventName="SignUpCompleted")
public class SignUpCompleted extends UserEvent {
}
