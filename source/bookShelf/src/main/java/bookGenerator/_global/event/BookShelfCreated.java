package bookGenerator._global.event;

import lombok.NoArgsConstructor;

import bookGenerator._global.eventBase.EventNameAnnotation;
import bookGenerator._global.eventBase.BookShelfEvent;
import bookGenerator.domain.BookShelf;

@NoArgsConstructor
@EventNameAnnotation(eventName="BookShelfCreated")
public class BookShelfCreated extends BookShelfEvent {
    public BookShelfCreated(BookShelf aggregate) {
        super(aggregate);
    }
}
