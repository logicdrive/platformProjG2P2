package bookGenerator._global.event;

import lombok.NoArgsConstructor;

import bookGenerator._global.eventBase.EventNameAnnotation;
import bookGenerator._global.eventBase.IndexEvent;
import bookGenerator.domain.Index;

@NoArgsConstructor
@EventNameAnnotation(eventName="IndexEdited")
public class IndexEdited extends IndexEvent {
    public IndexEdited(Index aggregate) {
        super(aggregate);
    }
}
