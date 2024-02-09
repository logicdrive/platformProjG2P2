package bookGenerator._global.eventBase;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import bookGenerator._global.infra.AbstractEvent;

import bookGenerator.domain.BookShelfBook;

@Data
@ToString
@EqualsAndHashCode(callSuper=false)
public class BookShelfBookEvent extends AbstractEvent {
	protected Long id;
	protected Long bookId;
	protected Date createdDate;
	protected Date updatedDate;

    public BookShelfBookEvent(BookShelfBook aggregate) {
        super(aggregate);
    }

    public BookShelfBookEvent() {
        super();
    }
}
