package bookGenerator._global.event;

import lombok.NoArgsConstructor;

import bookGenerator._global.eventBase.EventNameAnnotation;
import bookGenerator._global.eventBase.BookShelfBookEvent;
import bookGenerator.domain.BookShelfBook;

@EventNameAnnotation(eventName="BookShelfBookDeleted")
@NoArgsConstructor
public class BookShelfBookDeleted extends BookShelfBookEvent {
    public BookShelfBookDeleted(BookShelfBook aggregate) {
        super(aggregate);
    }
}
