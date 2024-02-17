package bookGenerator.problem.event;

import lombok.NoArgsConstructor;

import bookGenerator._global.eventBase.EventNameAnnotation;
import bookGenerator._global.eventBase.ProblemEvent;

@NoArgsConstructor
@EventNameAnnotation(eventName="ProblemDeleted")
public class ProblemDeleted extends ProblemEvent {
}
