package bookGenerator.book.event;

import lombok.NoArgsConstructor;

import bookGenerator._global.eventBase.EventNameAnnotation;
import bookGenerator._global.eventBase.BookEvent;

@NoArgsConstructor
@EventNameAnnotation(eventName="EmptyBookCreated")
public class EmptyBookCreated extends BookEvent {
}
