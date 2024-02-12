package bookGenerator._global.event;

import lombok.NoArgsConstructor;

import bookGenerator._global.eventBase.BookEvent;
import bookGenerator._global.eventBase.EventNameAnnotation;

@NoArgsConstructor
@EventNameAnnotation(eventName="EmptyBookCreated")
public class EmptyBookCreated extends BookEvent {
}
