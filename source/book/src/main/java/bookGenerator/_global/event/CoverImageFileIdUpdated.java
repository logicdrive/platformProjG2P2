package bookGenerator._global.event;

import lombok.NoArgsConstructor;

import bookGenerator._global.eventBase.EventNameAnnotation;
import bookGenerator._global.eventBase.BookEvent;
import bookGenerator.domain.Book;

@NoArgsConstructor
@EventNameAnnotation(eventName="CoverImageFileIdUpdated")
public class CoverImageFileIdUpdated extends BookEvent {
    public CoverImageFileIdUpdated(Book aggregate) {
        super(aggregate);
    }
}
