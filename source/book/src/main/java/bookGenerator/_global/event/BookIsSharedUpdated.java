package bookGenerator._global.event;

import lombok.NoArgsConstructor;

import bookGenerator._global.eventBase.EventNameAnnotation;
import bookGenerator._global.eventBase.BookEvent;
import bookGenerator.domain.Book;

@NoArgsConstructor
@EventNameAnnotation(eventName="BookIsSharedUpdated")
public class BookIsSharedUpdated extends BookEvent {
    public BookIsSharedUpdated(Book aggregate) {
        super(aggregate);
    }
}
