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
@EventNameAnnotation(eventName="CoverImageGenerationRequestedByFile")
public class CoverImageGenerationRequestedByFile extends FileEvent {
    String query;

    public CoverImageGenerationRequestedByFile(File aggregate, String query) {
        super(aggregate);
        this.query = query;
    }
}
