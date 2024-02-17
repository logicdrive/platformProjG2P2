package bookGenerator._global.eventBase;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import bookGenerator._global.infra.AbstractEvent;

@Data
@ToString
@EqualsAndHashCode(callSuper=false)
public class TagEvent extends AbstractEvent {
	protected Long id;
	protected Long bookId;
	protected String name;
	protected Date createdDate;
	protected Date updatedDate;

    public TagEvent() {
        super();
    }
}
