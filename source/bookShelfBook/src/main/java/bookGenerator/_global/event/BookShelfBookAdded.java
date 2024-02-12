package bookGenerator._global.event;

import lombok.NoArgsConstructor;

import bookGenerator._global.eventBase.EventNameAnnotation;
import bookGenerator._global.eventBase.BookShelfBookEvent;
import bookGenerator.domain.BookShelfBook;

@NoArgsConstructor
@EventNameAnnotation(eventName="BookShelfBookAdded")
public class BookShelfBookAdded extends BookShelfBookEvent {
    public BookShelfBookAdded(BookShelfBook aggregate) {
        super(aggregate);
    }
}
