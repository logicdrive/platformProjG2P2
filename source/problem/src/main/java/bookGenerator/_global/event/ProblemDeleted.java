package bookGenerator._global.event;

import lombok.NoArgsConstructor;

import bookGenerator._global.eventBase.EventNameAnnotation;
import bookGenerator._global.eventBase.ProblemEvent;
import bookGenerator.domain.Problem;

@NoArgsConstructor
@EventNameAnnotation(eventName="ProblemDeleted")
public class ProblemDeleted extends ProblemEvent {
    public ProblemDeleted(Problem aggregate) {
        super(aggregate);
    }
}
