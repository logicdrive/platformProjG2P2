package bookGenerator._global.event;

import lombok.NoArgsConstructor;

import bookGenerator._global.eventBase.EventNameAnnotation;
import bookGenerator._global.eventBase.FileEvent;
import bookGenerator.domain.File;

@EventNameAnnotation(eventName="ContentImageInfoUploaded")
@NoArgsConstructor
public class ContentImageInfoUploaded extends FileEvent {
    public ContentImageInfoUploaded(File aggregate) {
        super(aggregate);
    }
}
