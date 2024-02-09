package bookGenerator._global.event;

import lombok.NoArgsConstructor;

import bookGenerator._global.eventBase.EventNameAnnotation;
import bookGenerator._global.eventBase.TagEvent;
import bookGenerator.domain.Tag;

@EventNameAnnotation(eventName="TagGenerationRequested")
@NoArgsConstructor
public class TagGenerationRequested extends TagEvent {
    public TagGenerationRequested(Tag aggregate) {
        super(aggregate);
    }
}
