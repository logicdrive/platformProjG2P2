package bookGenerator._global.event;

import lombok.NoArgsConstructor;

import bookGenerator._global.eventBase.EventNameAnnotation;
import bookGenerator._global.eventBase.ProblemEvent;
import bookGenerator.domain.Problem;

@EventNameAnnotation(eventName="ProblemDeletedByFail")
@NoArgsConstructor
public class ProblemDeletedByFail extends ProblemEvent {
    public ProblemDeletedByFail(Problem aggregate) {
        super(aggregate);
    }
}
