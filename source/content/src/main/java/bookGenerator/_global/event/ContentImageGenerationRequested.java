package bookGenerator._global.event;

import lombok.NoArgsConstructor;

import bookGenerator._global.eventBase.EventNameAnnotation;
import bookGenerator._global.eventBase.ContentEvent;
import bookGenerator.domain.Content;

@EventNameAnnotation(eventName="ContentImageGenerationRequested")
@NoArgsConstructor
public class ContentImageGenerationRequested extends ContentEvent {
    public ContentImageGenerationRequested(Content aggregate) {
        super(aggregate);
    }
}
