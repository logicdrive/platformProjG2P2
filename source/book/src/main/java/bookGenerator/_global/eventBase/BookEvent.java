package bookGenerator._global.eventBase;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import bookGenerator._global.infra.AbstractEvent;

import bookGenerator.domain.Book;

@Data
@ToString
@EqualsAndHashCode(callSuper=false)
public class BookEvent extends AbstractEvent {
	protected Long id;
	protected String createrId;
	protected Long coverImageFileId;
	protected String title;
	protected Boolean isShared;
	protected Date createdDate;
	protected Date updatedDate;

    public BookEvent(Book aggregate) {
        super(aggregate);
    }

    public BookEvent() {
        super();
    }
}
