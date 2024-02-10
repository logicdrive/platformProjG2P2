package bookGenerator._global.event;

import lombok.NoArgsConstructor;

import bookGenerator._global.eventBase.EventNameAnnotation;
import bookGenerator._global.infra.AbstractEvent;

@EventNameAnnotation(eventName="TagGenerationRequested")
@NoArgsConstructor
public class TagGenerationRequested extends AbstractEvent {
    Long bookId;

    public TagGenerationRequested(Long bookId) {
        super();
        this.bookId = bookId;
    }
}
