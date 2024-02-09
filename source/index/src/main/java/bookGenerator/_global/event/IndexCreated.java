package bookGenerator._global.event;

import lombok.NoArgsConstructor;

import bookGenerator._global.eventBase.EventNameAnnotation;
import bookGenerator._global.eventBase.IndexEvent;
import bookGenerator.domain.Index;

@EventNameAnnotation(eventName="IndexCreated")
@NoArgsConstructor
public class IndexCreated extends IndexEvent {
    public IndexCreated(Index aggregate) {
        super(aggregate);
    }
}
