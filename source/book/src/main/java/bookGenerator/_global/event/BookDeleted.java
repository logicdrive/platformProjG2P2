package bookGenerator._global.event;

import lombok.NoArgsConstructor;

import bookGenerator._global.eventBase.EventNameAnnotation;
import bookGenerator._global.eventBase.BookEvent;
import bookGenerator.domain.Book;

@EventNameAnnotation(eventName="BookDeleted")
@NoArgsConstructor
public class BookDeleted extends BookEvent {
    public BookDeleted(Book aggregate) {
        super(aggregate);
    }
}
