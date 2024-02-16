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
@EventNameAnnotation(eventName="TagGenerationFailed")
public class TagGenerationFailed extends AbstractEvent {
	private Long bookId;

    public TagGenerationFailed(Long bookId) {
        this.bookId = bookId;
    }
}
