package bookGenerator._global.event;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import bookGenerator._global.eventBase.EventNameAnnotation;
import bookGenerator._global.infra.AbstractEvent;

@Data
@ToString
@EqualsAndHashCode(callSuper=false)
@EventNameAnnotation(eventName="ProblemGenerationRequsted")
public class ProblemGenerationRequsted extends AbstractEvent {
    private Long indexId;

    public ProblemGenerationRequsted() {
        super();
    }
}
