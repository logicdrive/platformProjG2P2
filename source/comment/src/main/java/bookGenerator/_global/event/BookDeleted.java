package bookGenerator._global.event;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import bookGenerator._global.eventBase.EventNameAnnotation;
import bookGenerator._global.eventBase.BookEvent;

@NoArgsConstructor
@Getter
@Setter

@EventNameAnnotation(eventName = "BookDeleted")
public class BookDeleted extends BookEvent {
    private Long bookId;

}
