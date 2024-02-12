package bookGenerator._global.event;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import bookGenerator._global.eventBase.EventNameAnnotation;
import bookGenerator._global.eventBase.FileEvent;
import bookGenerator.domain.File;

@Data
@NoArgsConstructor
@ToString(callSuper=true)
@EqualsAndHashCode(callSuper=false)
@EventNameAnnotation(eventName="ContentImageInfoUploaded")
public class ContentImageInfoUploaded extends FileEvent {
    private Long contentId;

    public ContentImageInfoUploaded(File aggregate, Long contentId) {
        super(aggregate);
        this.contentId = contentId;
    }
}
