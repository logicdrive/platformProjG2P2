package bookGenerator._global.event;

import lombok.NoArgsConstructor;

import bookGenerator._global.eventBase.EventNameAnnotation;
import bookGenerator._global.eventBase.ContentEvent;
import bookGenerator.domain.Content;

@NoArgsConstructor
@EventNameAnnotation(eventName="ContentImageGenerationRequested")
public class ContentImageGenerationRequested extends ContentEvent {
    public ContentImageGenerationRequested(Content aggregate) {
        super(aggregate);
    }
}