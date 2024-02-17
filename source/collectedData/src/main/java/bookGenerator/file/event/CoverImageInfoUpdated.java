package bookGenerator.file.event;

import lombok.NoArgsConstructor;

import bookGenerator._global.eventBase.EventNameAnnotation;
import bookGenerator._global.eventBase.FileEvent;

@NoArgsConstructor
@EventNameAnnotation(eventName="CoverImageInfoUpdated")
public class CoverImageInfoUpdated extends FileEvent {
}
