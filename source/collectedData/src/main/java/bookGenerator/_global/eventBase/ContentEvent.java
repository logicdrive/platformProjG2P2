package bookGenerator._global.eventBase;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import bookGenerator._global.infra.AbstractEvent;

@Data
@ToString
@EqualsAndHashCode(callSuper=false)
public class ContentEvent extends AbstractEvent {
	protected Long id;
	protected Long imageFileId;
	protected Long indexId;
	protected String content;
	protected Date createdDate;
	protected Date updatedDate;

    public ContentEvent() {
        super();
    }
}
