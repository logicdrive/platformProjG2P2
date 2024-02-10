package bookGenerator._global.event;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import bookGenerator._global.eventBase.EventNameAnnotation;
import bookGenerator._global.eventBase.FileEvent;

@Data
@ToString
@EqualsAndHashCode(callSuper=false)
@EventNameAnnotation(eventName="ContentImageInfoUploaded")
public class ContentImageInfoUploaded extends FileEvent {
	private Long contentId;

    public ContentImageInfoUploaded() {
        super();
    }
}
