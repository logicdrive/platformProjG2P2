package bookGenerator._global.event;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import bookGenerator._global.eventBase.EventNameAnnotation;
import bookGenerator._global.infra.AbstractEvent;

@Data
@ToString
@EqualsAndHashCode(callSuper=false)
@EventNameAnnotation(eventName="ProblemGenerationFailed")
public class ProblemGenerationFailed extends AbstractEvent {
	private Long problemId;

    public ProblemGenerationFailed() {
        super();
    }
}
