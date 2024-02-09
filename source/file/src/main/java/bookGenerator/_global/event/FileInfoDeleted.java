package bookGenerator._global.event;

import lombok.NoArgsConstructor;

import bookGenerator._global.eventBase.EventNameAnnotation;
import bookGenerator._global.eventBase.FileEvent;
import bookGenerator.domain.File;

@EventNameAnnotation(eventName="FileInfoDeleted")
@NoArgsConstructor
public class FileInfoDeleted extends FileEvent {
    public FileInfoDeleted(File aggregate) {
        super(aggregate);
    }
}
