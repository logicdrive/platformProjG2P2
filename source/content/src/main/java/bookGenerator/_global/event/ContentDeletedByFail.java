package bookGenerator._global.event;

import lombok.NoArgsConstructor;

import bookGenerator._global.eventBase.EventNameAnnotation;
import bookGenerator._global.eventBase.ContentEvent;
import bookGenerator.domain.Content;

@NoArgsConstructor
@EventNameAnnotation(eventName="ContentDeletedByFail")
public class ContentDeletedByFail extends ContentEvent {
    public ContentDeletedByFail(Content aggregate) {
        super(aggregate);
    }
}
