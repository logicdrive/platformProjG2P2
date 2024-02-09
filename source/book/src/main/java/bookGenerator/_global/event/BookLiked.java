package bookGenerator._global.event;

import lombok.NoArgsConstructor;

import bookGenerator._global.eventBase.EventNameAnnotation;
import bookGenerator._global.eventBase.BookEvent;
import bookGenerator.domain.Book;

@EventNameAnnotation(eventName="BookLiked")
@NoArgsConstructor
public class BookLiked extends BookEvent {
    public BookLiked(Book aggregate) {
        super(aggregate);
    }
}
