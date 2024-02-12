package bookGenerator._global.event;

import lombok.NoArgsConstructor;

import bookGenerator._global.eventBase.EventNameAnnotation;
import bookGenerator._global.eventBase.BookShelfEvent;
import bookGenerator.domain.BookShelf;

@NoArgsConstructor
@EventNameAnnotation(eventName="BookShelfTitleUpdated")
public class BookShelfTitleUpdated extends BookShelfEvent {
    public BookShelfTitleUpdated(BookShelf aggregate) {
        super(aggregate);
    }
}
