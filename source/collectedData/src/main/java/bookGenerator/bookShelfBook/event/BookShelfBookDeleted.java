package bookGenerator.bookShelfBook.event;

import lombok.NoArgsConstructor;

import bookGenerator._global.eventBase.EventNameAnnotation;
import bookGenerator._global.eventBase.BookShelfBookEvent;

@NoArgsConstructor
@EventNameAnnotation(eventName="BookShelfBookDeleted")
public class BookShelfBookDeleted extends BookShelfBookEvent {
}
