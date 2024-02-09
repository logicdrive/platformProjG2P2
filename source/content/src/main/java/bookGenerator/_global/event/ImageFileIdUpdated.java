package bookGenerator._global.event;

import lombok.NoArgsConstructor;

import bookGenerator._global.eventBase.EventNameAnnotation;
import bookGenerator._global.eventBase.ContentEvent;
import bookGenerator.domain.Content;

@EventNameAnnotation(eventName="ImageFileIdUpdated")
@NoArgsConstructor
public class ImageFileIdUpdated extends ContentEvent {
    public ImageFileIdUpdated(Content aggregate) {
        super(aggregate);
    }
}
