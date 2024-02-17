package bookGenerator.bookShelf.event;

import lombok.NoArgsConstructor;

import bookGenerator._global.eventBase.EventNameAnnotation;
import bookGenerator._global.eventBase.BookShelfEvent;

@NoArgsConstructor
@EventNameAnnotation(eventName="BookShelfIsSharedUpdated")
public class BookShelfIsSharedUpdated extends BookShelfEvent {
}
