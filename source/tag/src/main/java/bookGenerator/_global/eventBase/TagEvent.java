package bookGenerator._global.eventBase;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import bookGenerator._global.infra.AbstractEvent;

import bookGenerator.domain.Tag;

@Data
@ToString
@EqualsAndHashCode(callSuper=false)
public class TagEvent extends AbstractEvent {
	protected Long id;
	protected Long bookId;
	protected String name;
	protected Date createdDate;
	protected Date updatedDate;

    public TagEvent(Tag aggregate) {
        super(aggregate);
    }

    public TagEvent() {
        super();
    }
}
