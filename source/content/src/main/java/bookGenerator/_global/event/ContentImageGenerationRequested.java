package bookGenerator._global.event;

import lombok.NoArgsConstructor;

import bookGenerator._global.eventBase.EventNameAnnotation;
import bookGenerator._global.infra.AbstractEvent;

@EventNameAnnotation(eventName="ContentImageGenerationRequested")
@NoArgsConstructor
public class ContentImageGenerationRequested extends AbstractEvent {
    private Long indexId;;

    public ContentImageGenerationRequested(Long indexId) {
        super();
        this.indexId = indexId;
    }
}
