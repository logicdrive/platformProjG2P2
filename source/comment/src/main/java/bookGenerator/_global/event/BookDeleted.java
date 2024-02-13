package bookGenerator._global.event;

import lombok.NoArgsConstructor;

import bookGenerator._global.eventBase.EventNameAnnotation;
import bookGenerator._global.eventBase.BookEvent;
import lombok.Setter;
import lombok.Getter;

@NoArgsConstructor
@Getter
@Setter
@EventNameAnnotation(eventName = "BookDeleted")
public class BookDeleted extends BookEvent {
    private Long bookId;

    public BookDeleted(Long bookId) {
        this.bookId = bookId;
    }
}
