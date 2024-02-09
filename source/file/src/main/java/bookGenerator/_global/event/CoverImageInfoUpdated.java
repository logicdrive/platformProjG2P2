package bookGenerator._global.event;

import lombok.NoArgsConstructor;

import bookGenerator._global.eventBase.EventNameAnnotation;
import bookGenerator._global.eventBase.FileEvent;
import bookGenerator.domain.File;

@EventNameAnnotation(eventName="CoverImageInfoUpdated")
@NoArgsConstructor
public class CoverImageInfoUpdated extends FileEvent {
    public CoverImageInfoUpdated(File aggregate) {
        super(aggregate);
    }
}
