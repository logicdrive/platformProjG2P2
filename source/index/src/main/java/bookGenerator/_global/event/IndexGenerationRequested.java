package bookGenerator._global.event;

import lombok.NoArgsConstructor;

import bookGenerator._global.eventBase.EventNameAnnotation;
import bookGenerator._global.eventBase.IndexEvent;
import bookGenerator.domain.Index;

@EventNameAnnotation(eventName="IndexGenerationRequested")
@NoArgsConstructor
public class IndexGenerationRequested extends IndexEvent {
    public IndexGenerationRequested(Index aggregate) {
        super(aggregate);
    }
}
