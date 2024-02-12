package bookGenerator._global.event;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import bookGenerator._global.eventBase.EventNameAnnotation;
import bookGenerator._global.eventBase.BookEvent;
import bookGenerator.domain.Book;

@Data
@NoArgsConstructor
@ToString(callSuper=true)
@EqualsAndHashCode(callSuper=false)
@EventNameAnnotation(eventName="BookLiked")
public class BookLiked extends BookEvent {
    private Long userId;

    public BookLiked(Book aggregate, Long userId) {
        super(aggregate);
        this.userId = userId;
    }
}
