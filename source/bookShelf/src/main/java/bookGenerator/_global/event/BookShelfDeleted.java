package bookGenerator._global.event;

import lombok.NoArgsConstructor;

import bookGenerator._global.eventBase.EventNameAnnotation;
import bookGenerator._global.eventBase.BookShelfEvent;
import bookGenerator.domain.BookShelf;

@NoArgsConstructor
@EventNameAnnotation(eventName="BookShelfDeleted")
public class BookShelfDeleted extends BookShelfEvent {
    public BookShelfDeleted(BookShelf aggregate) {
        super(aggregate);
    }
}
