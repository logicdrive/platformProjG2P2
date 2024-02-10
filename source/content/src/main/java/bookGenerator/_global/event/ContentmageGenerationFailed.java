package bookGenerator._global.event;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import bookGenerator._global.eventBase.EventNameAnnotation;
import bookGenerator._global.infra.AbstractEvent;

@Data
@ToString
@EqualsAndHashCode(callSuper=false)
@EventNameAnnotation(eventName="ContentmageGenerationFailed")
public class ContentmageGenerationFailed extends AbstractEvent {
	private Long id;
	private Long contentId;

    public ContentmageGenerationFailed() {
        super();
    }
}
