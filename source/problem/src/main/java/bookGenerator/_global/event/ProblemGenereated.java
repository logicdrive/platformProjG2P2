package bookGenerator._global.event;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import bookGenerator._global.eventBase.EventNameAnnotation;
import bookGenerator._global.infra.AbstractEvent;

@Data
@ToString
@EqualsAndHashCode(callSuper=false)
@EventNameAnnotation(eventName="ProblemGenereated")
public class ProblemGenereated extends AbstractEvent {
	private Long id;
	private Long problemId;
	private String content;
	private String answer;
	private String priority;

    public ProblemGenereated() {
        super();
    }
}
