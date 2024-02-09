package bookGenerator._global.event;

import lombok.NoArgsConstructor;

import bookGenerator._global.eventBase.EventNameAnnotation;
import bookGenerator._global.eventBase.BookEvent;
import bookGenerator.domain.Book;

@EventNameAnnotation(eventName="CoverImageUpdateRequested")
@NoArgsConstructor
public class CoverImageUpdateRequested extends BookEvent {
    public CoverImageUpdateRequested(Book aggregate) {
        super(aggregate);
    }
}
