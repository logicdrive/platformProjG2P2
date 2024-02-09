package bookGenerator._global.event;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import bookGenerator._global.eventBase.EventNameAnnotation;
import bookGenerator._global.infra.AbstractEvent;

@Data
@ToString
@EqualsAndHashCode(callSuper=false)
@EventNameAnnotation(eventName="TagGernerated")
public class TagGernerated extends AbstractEvent {
	private Long id;
	private Long bookId;
	private String tagName;

    public TagGernerated() {
        super();
    }
}
