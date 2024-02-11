package bookGenerator._global.event;

import lombok.NoArgsConstructor;

import bookGenerator._global.eventBase.EventNameAnnotation;
import bookGenerator._global.eventBase.BookShelfEvent;
import bookGenerator.domain.BookShelf;

@EventNameAnnotation(eventName="BookShelfTitleUpdated")
@NoArgsConstructor
public class BookShelfTitleUpdated extends BookShelfEvent {
    public BookShelfTitleUpdated(BookShelf aggregate) {
        super(aggregate);
    }
}
