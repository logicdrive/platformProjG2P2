package bookGenerator._global.event;

import lombok.NoArgsConstructor;

import bookGenerator._global.eventBase.BookShelfBookEvent;
import bookGenerator._global.eventBase.EventNameAnnotation;

@NoArgsConstructor
@EventNameAnnotation(eventName="BookShelfBookDeleted")
public class BookShelfBookDeleted extends BookShelfBookEvent {
}
