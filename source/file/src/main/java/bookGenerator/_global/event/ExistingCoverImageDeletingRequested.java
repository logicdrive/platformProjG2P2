package bookGenerator._global.event;

import lombok.NoArgsConstructor;

import bookGenerator._global.eventBase.EventNameAnnotation;
import bookGenerator._global.eventBase.FileEvent;
import bookGenerator.domain.File;

@EventNameAnnotation(eventName="ExistingCoverImageDeletingRequested")
@NoArgsConstructor
public class ExistingCoverImageDeletingRequested extends FileEvent {
    public ExistingCoverImageDeletingRequested(File aggregate) {
        super(aggregate);
    }
}
