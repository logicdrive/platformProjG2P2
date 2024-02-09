package bookGenerator._global.event;

import lombok.NoArgsConstructor;

import bookGenerator._global.eventBase.EventNameAnnotation;
import bookGenerator._global.eventBase.TagEvent;
import bookGenerator.domain.Tag;

@EventNameAnnotation(eventName="TagCreated")
@NoArgsConstructor
public class TagCreated extends TagEvent {
    public TagCreated(Tag aggregate) {
        super(aggregate);
    }
}
