package bookGenerator._global.event;

import lombok.NoArgsConstructor;

import bookGenerator._global.eventBase.EventNameAnnotation;
import bookGenerator._global.eventBase.FileEvent;
import bookGenerator.domain.File;

@NoArgsConstructor
@EventNameAnnotation(eventName = "CoverImageGenerationRequestedByFile")
public class CoverImageGenerationRequestedByFile extends FileEvent {

    public CoverImageGenerationRequestedByFile(File aggregate) {
        super(aggregate);
    }

}
