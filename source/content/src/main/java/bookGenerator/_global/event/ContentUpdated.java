package bookGenerator._global.event;

import lombok.NoArgsConstructor;

import bookGenerator._global.eventBase.EventNameAnnotation;
import bookGenerator._global.eventBase.ContentEvent;
import bookGenerator.domain.Content;

@NoArgsConstructor
@EventNameAnnotation(eventName="ContentUpdated")
public class ContentUpdated extends ContentEvent {
    public ContentUpdated(Content aggregate) {
        super(aggregate);
    }
}
