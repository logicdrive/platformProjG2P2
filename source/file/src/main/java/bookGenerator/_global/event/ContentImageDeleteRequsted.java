package bookGenerator._global.event;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import bookGenerator._global.eventBase.ContentEvent;
import bookGenerator._global.eventBase.EventNameAnnotation;

@Data
@ToString
@EqualsAndHashCode(callSuper=false)
@EventNameAnnotation(eventName="ContentImageDeleteRequsted")
public class ContentImageDeleteRequsted extends ContentEvent {
    public ContentImageDeleteRequsted() {
        super();
    }
}
