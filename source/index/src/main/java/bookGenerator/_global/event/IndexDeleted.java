package bookGenerator._global.event;

import lombok.NoArgsConstructor;

import bookGenerator._global.eventBase.EventNameAnnotation;
import bookGenerator._global.eventBase.IndexEvent;
import bookGenerator.domain.Index;

@EventNameAnnotation(eventName="IndexDeleted")
@NoArgsConstructor
public class IndexDeleted extends IndexEvent {
    public IndexDeleted(Index aggregate) {
        super(aggregate);
    }
}
