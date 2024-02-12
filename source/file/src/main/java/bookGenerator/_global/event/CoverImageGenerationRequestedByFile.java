package bookGenerator._global.event;

import lombok.NoArgsConstructor;

import bookGenerator._global.eventBase.EventNameAnnotation;
import bookGenerator._global.eventBase.FileEvent;

@NoArgsConstructor
@EventNameAnnotation(eventName="CoverImageGenerationRequestedByFile")
public class CoverImageGenerationRequestedByFile extends FileEvent {
}
