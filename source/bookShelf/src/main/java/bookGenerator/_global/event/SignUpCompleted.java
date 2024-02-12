package bookGenerator._global.event;

import lombok.NoArgsConstructor;

import bookGenerator._global.eventBase.EventNameAnnotation;
import bookGenerator._global.eventBase.UserEvent;

@NoArgsConstructor
@EventNameAnnotation(eventName="SignUpCompleted")
public class SignUpCompleted extends UserEvent {
}