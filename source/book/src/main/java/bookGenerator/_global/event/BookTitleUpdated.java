package bookGenerator._global.event;

import lombok.NoArgsConstructor;

import bookGenerator._global.eventBase.EventNameAnnotation;
import bookGenerator._global.eventBase.BookEvent;
import bookGenerator.domain.Book;

@NoArgsConstructor
@EventNameAnnotation(eventName="BookTitleUpdated")
public class BookTitleUpdated extends BookEvent {
    public BookTitleUpdated(Book aggregate) {
        super(aggregate);
    }
}
