package bookGenerator._global.event;

import lombok.NoArgsConstructor;

import bookGenerator._global.eventBase.EventNameAnnotation;
import bookGenerator._global.infra.AbstractEvent;

@EventNameAnnotation(eventName="IndexGenerationRequested")
@NoArgsConstructor
public class IndexGenerationRequested extends AbstractEvent {
    Long bookId;

    public IndexGenerationRequested(Long bookId) {
        super();
        this.bookId = bookId;
    }
}
