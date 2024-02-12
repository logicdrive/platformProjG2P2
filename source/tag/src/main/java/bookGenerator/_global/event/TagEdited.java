package bookGenerator._global.event;

import lombok.NoArgsConstructor;

import bookGenerator._global.eventBase.EventNameAnnotation;
import bookGenerator._global.eventBase.TagEvent;
import bookGenerator.domain.Tag;

@NoArgsConstructor
@EventNameAnnotation(eventName="TagEdited")
public class TagEdited extends TagEvent {
    public TagEdited(Tag aggregate) {
        super(aggregate);
    }
}
