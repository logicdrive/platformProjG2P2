package bookGenerator._global.event;

import lombok.NoArgsConstructor;

import bookGenerator._global.eventBase.EventNameAnnotation;
import bookGenerator._global.eventBase.ContentEvent;
import bookGenerator.domain.Content;

@NoArgsConstructor
@EventNameAnnotation(eventName="ContentGenerationRequested")
public class ContentGenerationRequested extends ContentEvent {
    public ContentGenerationRequested(Content aggregate) {
        super(aggregate);
    }
}
