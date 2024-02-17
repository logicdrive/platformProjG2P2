package bookGenerator.bookShelf.event;

import lombok.NoArgsConstructor;

import bookGenerator._global.eventBase.EventNameAnnotation;
import bookGenerator._global.eventBase.BookShelfEvent;

@NoArgsConstructor
@EventNameAnnotation(eventName="BookShelfTitleUpdated")
public class BookShelfTitleUpdated extends BookShelfEvent {
}
