package bookGenerator._global.event;

import lombok.NoArgsConstructor;

import bookGenerator._global.eventBase.EventNameAnnotation;
import bookGenerator._global.eventBase.ProblemEvent;
import bookGenerator.domain.Problem;

@EventNameAnnotation(eventName="ProblemUpdated")
@NoArgsConstructor
public class ProblemUpdated extends ProblemEvent {
    public ProblemUpdated(Problem aggregate) {
        super(aggregate);
    }
}
