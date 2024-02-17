package bookGenerator.problem.event;

import lombok.NoArgsConstructor;

import bookGenerator._global.eventBase.EventNameAnnotation;
import bookGenerator._global.eventBase.ProblemEvent;

@NoArgsConstructor
@EventNameAnnotation(eventName="ProblemCreated")
public class ProblemCreated extends ProblemEvent {
}
