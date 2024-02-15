package bookGenerator._global.event;

import lombok.NoArgsConstructor;
import bookGenerator._global.eventBase.EventNameAnnotation;
import bookGenerator._global.eventBase.BookEvent;

@NoArgsConstructor

@EventNameAnnotation(eventName = "BookDeleted")
public class BookDeleted extends BookEvent {

}
