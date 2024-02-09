package bookGenerator._global.eventBase;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import bookGenerator._global.infra.AbstractEvent;

import bookGenerator.domain.Problem;

@Data
@ToString
@EqualsAndHashCode(callSuper=false)
public class ProblemEvent extends AbstractEvent {
	protected Long id;
	protected Long indexId;
	protected String content;
	protected String answer;
	protected Long priority;
	protected Date createdDate;
	protected Date updatedDate;

    public ProblemEvent(Problem aggregate) {
        super(aggregate);
    }

    public ProblemEvent() {
        super();
    }
}
