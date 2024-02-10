package bookGenerator._global.event;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import bookGenerator._global.eventBase.EventNameAnnotation;
import bookGenerator._global.eventBase.FileEvent;

@Data
@ToString
@EqualsAndHashCode(callSuper=false)
@EventNameAnnotation(eventName="EmptyCoverImageInfoCreated")
public class EmptyCoverImageInfoCreated extends FileEvent {
	private Long bookId;

    public EmptyCoverImageInfoCreated() {
        super();
    }
}
