package bookGenerator._global.event;

import lombok.NoArgsConstructor;

import bookGenerator._global.eventBase.EventNameAnnotation;
import bookGenerator._global.eventBase.BookEvent;
import bookGenerator.domain.Book;

@EventNameAnnotation(eventName="EmptyBookCreated")
@NoArgsConstructor
public class EmptyBookCreated extends BookEvent {
    public EmptyBookCreated(Book aggregate) {
        super(aggregate);
    }
}
