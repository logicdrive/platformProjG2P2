package bookGenerator._global.eventBase;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import bookGenerator._global.infra.AbstractEvent;

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

    public BookEvent() {
        super();
    }
}
