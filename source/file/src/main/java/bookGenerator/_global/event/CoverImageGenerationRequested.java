package bookGenerator._global.event;

import lombok.NoArgsConstructor;

import bookGenerator._global.eventBase.BookEvent;
import bookGenerator._global.eventBase.EventNameAnnotation;

@NoArgsConstructor
@EventNameAnnotation(eventName="CoverImageGenerationRequested")
public class CoverImageGenerationRequested extends BookEvent {
}
