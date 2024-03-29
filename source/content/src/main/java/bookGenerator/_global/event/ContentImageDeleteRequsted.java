package bookGenerator._global.event;

import lombok.NoArgsConstructor;

import bookGenerator._global.eventBase.EventNameAnnotation;
import bookGenerator._global.eventBase.ContentEvent;
import bookGenerator.domain.Content;

@NoArgsConstructor
@EventNameAnnotation(eventName="ContentImageDeleteRequsted")
public class ContentImageDeleteRequsted extends ContentEvent {
    public ContentImageDeleteRequsted(Content aggregate) {
        super(aggregate);
    }
}
