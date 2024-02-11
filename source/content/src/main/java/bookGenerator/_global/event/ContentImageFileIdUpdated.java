package bookGenerator._global.event;

import lombok.NoArgsConstructor;

import bookGenerator._global.eventBase.EventNameAnnotation;
import bookGenerator._global.eventBase.ContentEvent;
import bookGenerator.domain.Content;

@EventNameAnnotation(eventName="ContentImageFileIdUpdated")
@NoArgsConstructor
public class ContentImageFileIdUpdated extends ContentEvent {
    public ContentImageFileIdUpdated(Content aggregate) {
        super(aggregate);
    }
}
