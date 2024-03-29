package bookGenerator._global.event;

import lombok.NoArgsConstructor;

import bookGenerator._global.eventBase.EventNameAnnotation;
import bookGenerator._global.eventBase.BookEvent;
import bookGenerator.domain.Book;

@NoArgsConstructor
@EventNameAnnotation(eventName="CoverImageGenerationRequested")
public class CoverImageGenerationRequested extends BookEvent {
    public CoverImageGenerationRequested(Book aggregate) {
        super(aggregate);
    }
}
