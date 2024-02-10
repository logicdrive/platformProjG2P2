package bookGenerator._global.event;

import lombok.NoArgsConstructor;

import bookGenerator._global.eventBase.EventNameAnnotation;
import bookGenerator._global.eventBase.BookEvent;
import bookGenerator.domain.Book;

@EventNameAnnotation(eventName="BookLiked")
@NoArgsConstructor
public class BookLiked extends BookEvent {
    private Long userId;

    public BookLiked(Book aggregate, Long userId) {
        super(aggregate);
        this.userId = userId;
    }
}
