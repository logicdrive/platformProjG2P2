package bookGenerator._global.event;

import lombok.NoArgsConstructor;

import bookGenerator._global.eventBase.EventNameAnnotation;
import bookGenerator._global.eventBase.BookShelfEvent;
import bookGenerator.domain.BookShelf;

@EventNameAnnotation(eventName="BookShelfDeleted")
@NoArgsConstructor
public class BookShelfDeleted extends BookShelfEvent {
    public BookShelfDeleted(BookShelf aggregate) {
        super(aggregate);
    }
}
