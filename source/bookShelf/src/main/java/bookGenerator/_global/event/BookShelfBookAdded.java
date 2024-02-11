package bookGenerator._global.event;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import bookGenerator._global.eventBase.BookShelfBookEvent;
import bookGenerator._global.eventBase.EventNameAnnotation;

@Data
@ToString
@EqualsAndHashCode(callSuper=false)
@EventNameAnnotation(eventName="BookShelfBookAdded")
public class BookShelfBookAdded extends BookShelfBookEvent {
    public BookShelfBookAdded() {
        super();
    }
}
