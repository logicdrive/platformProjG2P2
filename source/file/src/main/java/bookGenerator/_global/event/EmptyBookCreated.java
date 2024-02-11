package bookGenerator._global.event;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import bookGenerator._global.eventBase.BookEvent;
import bookGenerator._global.eventBase.EventNameAnnotation;

@Data
@ToString
@EqualsAndHashCode(callSuper=false)
@EventNameAnnotation(eventName="EmptyBookCreated")
public class EmptyBookCreated extends BookEvent {
    public EmptyBookCreated() {
        super();
    }
}
