package bookGenerator.book.event;

import lombok.NoArgsConstructor;

import bookGenerator._global.eventBase.EventNameAnnotation;
import bookGenerator._global.eventBase.BookEvent;

@NoArgsConstructor
@EventNameAnnotation(eventName="BookIsSharedUpdated")
public class BookIsSharedUpdated extends BookEvent {
}
