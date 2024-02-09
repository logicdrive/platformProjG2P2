package bookGenerator._global.eventBase;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import bookGenerator._global.infra.AbstractEvent;

import bookGenerator.domain.Content;

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

    public ContentEvent(Content aggregate) {
        super(aggregate);
    }

    public ContentEvent() {
        super();
    }
}
