package bookGenerator._global.event;

import lombok.NoArgsConstructor;

import bookGenerator._global.eventBase.EventNameAnnotation;
import bookGenerator._global.eventBase.ProblemEvent;
import bookGenerator.domain.Problem;

@EventNameAnnotation(eventName="ProblemDeleted")
@NoArgsConstructor
public class ProblemDeleted extends ProblemEvent {
    public ProblemDeleted(Problem aggregate) {
        super(aggregate);
    }
}
