package bookGenerator.book.event;

import lombok.NoArgsConstructor;

import bookGenerator._global.eventBase.EventNameAnnotation;
import bookGenerator._global.eventBase.BookEvent;

@NoArgsConstructor
@EventNameAnnotation(eventName="CoverImageFileIdUpdated")
public class CoverImageFileIdUpdated extends BookEvent {
}
