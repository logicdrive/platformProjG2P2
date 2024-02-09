package bookGenerator._global.event;

import lombok.NoArgsConstructor;

import bookGenerator._global.eventBase.EventNameAnnotation;
import bookGenerator._global.eventBase.BookEvent;
import bookGenerator.domain.Book;

@EventNameAnnotation(eventName="CoverImageFileIdUpdated")
@NoArgsConstructor
public class CoverImageFileIdUpdated extends BookEvent {
    public CoverImageFileIdUpdated(Book aggregate) {
        super(aggregate);
    }
}
