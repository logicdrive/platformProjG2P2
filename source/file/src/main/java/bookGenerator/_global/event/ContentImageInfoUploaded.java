package bookGenerator._global.event;

import lombok.NoArgsConstructor;

import bookGenerator._global.eventBase.EventNameAnnotation;
import bookGenerator._global.eventBase.FileEvent;
import bookGenerator.domain.File;

@EventNameAnnotation(eventName="ContentImageInfoUploaded")
@NoArgsConstructor
public class ContentImageInfoUploaded extends FileEvent {
    private Long contentId;

    public ContentImageInfoUploaded(File aggregate, Long contentId) {
        super(aggregate);
        this.contentId = contentId;
    }
}
