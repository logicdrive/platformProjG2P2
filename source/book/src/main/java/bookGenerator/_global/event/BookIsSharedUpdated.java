package bookGenerator._global.event;

import lombok.NoArgsConstructor;

import bookGenerator._global.eventBase.EventNameAnnotation;
import bookGenerator._global.eventBase.BookEvent;
import bookGenerator.domain.Book;

@EventNameAnnotation(eventName="BookIsSharedUpdated")
@NoArgsConstructor
public class BookIsSharedUpdated extends BookEvent {
    public BookIsSharedUpdated(Book aggregate) {
        super(aggregate);
    }
}
