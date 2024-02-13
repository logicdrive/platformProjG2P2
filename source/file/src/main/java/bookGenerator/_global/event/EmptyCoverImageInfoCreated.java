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
@EventNameAnnotation(eventName="EmptyCoverImageInfoCreated")
public class EmptyCoverImageInfoCreated extends FileEvent {
    private Long bookId;

    public EmptyCoverImageInfoCreated(File aggregate, Long bookId) {
        super(aggregate);
        this.bookId = bookId;
    }
}
