package bookGenerator._global.event;

import lombok.NoArgsConstructor;

import bookGenerator._global.eventBase.EventNameAnnotation;
import bookGenerator._global.eventBase.BookShelfEvent;

@NoArgsConstructor
@EventNameAnnotation(eventName="BookShelfDeleted")
public class BookShelfDeleted extends BookShelfEvent {
}
