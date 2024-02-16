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
@EventNameAnnotation(eventName="TagGenerationRequested")
public class TagGenerationRequested extends AbstractEvent {
    Long bookId;
    String query;

    public TagGenerationRequested(Long bookId, String query) {
        this.bookId = bookId;
        this.query = query;
    }
}
