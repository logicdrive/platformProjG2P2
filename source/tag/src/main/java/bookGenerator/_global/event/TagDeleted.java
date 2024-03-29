package bookGenerator._global.event;

import lombok.NoArgsConstructor;

import bookGenerator._global.eventBase.EventNameAnnotation;
import bookGenerator._global.eventBase.TagEvent;
import bookGenerator.domain.Tag;

@NoArgsConstructor
@EventNameAnnotation(eventName="TagDeleted")
public class TagDeleted extends TagEvent {
    public TagDeleted(Tag aggregate) {
        super(aggregate);
    }
}
