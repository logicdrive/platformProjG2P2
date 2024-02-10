package bookGenerator._global.event;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import bookGenerator._global.eventBase.EventNameAnnotation;
import bookGenerator._global.infra.AbstractEvent;

@Data
@ToString
@EqualsAndHashCode(callSuper=false)
@EventNameAnnotation(eventName="IndexGenereated")
public class IndexGenereated extends AbstractEvent {
	private Long bookId;
	private String indexName;
	private Long priority;

    public IndexGenereated() {
        super();
    }
}
