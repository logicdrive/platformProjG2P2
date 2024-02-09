package bookGenerator._global.event;

import lombok.NoArgsConstructor;

import bookGenerator._global.eventBase.EventNameAnnotation;
import bookGenerator._global.eventBase.BookShelfEvent;
import bookGenerator.domain.BookShelf;

@EventNameAnnotation(eventName="BookCountUpdated")
@NoArgsConstructor
public class BookCountUpdated extends BookShelfEvent {
    public BookCountUpdated(BookShelf aggregate) {
        super(aggregate);
    }
}
