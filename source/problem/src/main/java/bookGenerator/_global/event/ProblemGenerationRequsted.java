package bookGenerator._global.event;

import lombok.NoArgsConstructor;

import bookGenerator._global.eventBase.EventNameAnnotation;
import bookGenerator._global.eventBase.ProblemEvent;
import bookGenerator.domain.Problem;

@EventNameAnnotation(eventName="ProblemGenerationRequsted")
@NoArgsConstructor
public class ProblemGenerationRequsted extends ProblemEvent {
    public ProblemGenerationRequsted(Problem aggregate) {
        super(aggregate);
    }
}
