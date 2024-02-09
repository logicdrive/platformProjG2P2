package bookGenerator._global.eventBase;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import bookGenerator._global.infra.AbstractEvent;

import bookGenerator.domain.BookShelf;

@Data
@ToString
@EqualsAndHashCode(callSuper=false)
public class BookShelfEvent extends AbstractEvent {
	protected Long id;
	protected Long createrId;
	protected String title;
	protected Long bookCount;
	protected Boolean isShared;
	protected Boolean isDeletable;
	protected Date createdDate;
	protected Date updatedDate;

    public BookShelfEvent(BookShelf aggregate) {
        super(aggregate);
    }

    public BookShelfEvent() {
        super();
    }
}
