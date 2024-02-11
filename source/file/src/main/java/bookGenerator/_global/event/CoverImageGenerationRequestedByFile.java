package bookGenerator._global.event;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import bookGenerator._global.eventBase.EventNameAnnotation;
import bookGenerator._global.eventBase.FileEvent;

@Data
@ToString
@EqualsAndHashCode(callSuper=false)
@EventNameAnnotation(eventName="CoverImageGenerationRequestedByFile")
public class CoverImageGenerationRequestedByFile extends FileEvent {
    public CoverImageGenerationRequestedByFile() {
        super();
    }
}
