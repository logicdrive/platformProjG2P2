package bookGenerator._global.event;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import bookGenerator._global.eventBase.EventNameAnnotation;
import bookGenerator._global.infra.AbstractEvent;

@Data
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
@EventNameAnnotation(eventName = "ProblemDeletedByFail")
public class ProblemDeletedByFail extends AbstractEvent {
    private Long indexId;
    private Long problemId;

    public ProblemDeletedByFail(Long problemId) {
        this.problemId = problemId;
    }

}
