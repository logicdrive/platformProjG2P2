package bookGenerator._global.eventBase;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import bookGenerator._global.infra.AbstractEvent;

import bookGenerator.domain.File;

@Data
@ToString
@EqualsAndHashCode(callSuper=false)
public class FileEvent extends AbstractEvent {
	protected Long id;
	protected String url;
	protected Date createdDate;
	protected Date updatedDate;

    public FileEvent(File aggregate) {
        super(aggregate);
    }

    public FileEvent() {
        super();
    }
}
