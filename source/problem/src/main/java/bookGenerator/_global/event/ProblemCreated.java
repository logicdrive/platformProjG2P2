package bookGenerator._global.event;

import lombok.NoArgsConstructor;

import bookGenerator._global.eventBase.EventNameAnnotation;
import bookGenerator._global.eventBase.ProblemEvent;
import bookGenerator.domain.Problem;

@EventNameAnnotation(eventName="ProblemCreated")
@NoArgsConstructor
public class ProblemCreated extends ProblemEvent {
    public ProblemCreated(Problem aggregate) {
        super(aggregate);
    }
}
