package bookGenerator._global.event;

import lombok.NoArgsConstructor;

import bookGenerator._global.eventBase.EventNameAnnotation;
import bookGenerator._global.eventBase.BookShelfEvent;
import bookGenerator.domain.BookShelf;

@EventNameAnnotation(eventName="BookShelfIsSharedUpdated")
@NoArgsConstructor
public class BookShelfIsSharedUpdated extends BookShelfEvent {
    public BookShelfIsSharedUpdated(BookShelf aggregate) {
        super(aggregate);
    }
}
