package bookGenerator._global.event;

import lombok.NoArgsConstructor;

import bookGenerator._global.eventBase.EventNameAnnotation;
import bookGenerator._global.eventBase.BookShelfEvent;
import bookGenerator.domain.BookShelf;

@EventNameAnnotation(eventName="BookShelfTItleUpdated")
@NoArgsConstructor
public class BookShelfTItleUpdated extends BookShelfEvent {
    public BookShelfTItleUpdated(BookShelf aggregate) {
        super(aggregate);
    }
}
