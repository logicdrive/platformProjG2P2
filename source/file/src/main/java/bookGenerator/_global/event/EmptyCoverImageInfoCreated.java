package bookGenerator._global.event;

import lombok.NoArgsConstructor;

import bookGenerator._global.eventBase.EventNameAnnotation;
import bookGenerator._global.eventBase.FileEvent;
import bookGenerator.domain.File;

@NoArgsConstructor
@EventNameAnnotation(eventName="EmptyCoverImageInfoCreated")
public class EmptyCoverImageInfoCreated extends FileEvent {
    public EmptyCoverImageInfoCreated(File aggregate) {
        super(aggregate);
    }
}
