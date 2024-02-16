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
@EqualsAndHashCode(callSuper=false)
@EventNameAnnotation(eventName="ProblemGenerationRequested")
public class ProblemGenerationRequested extends AbstractEvent {
    private Long indexId;
    private String query;

    public ProblemGenerationRequested(Long indexId, String query) {
        this.indexId = indexId;
        this.query = query;
    }
}
