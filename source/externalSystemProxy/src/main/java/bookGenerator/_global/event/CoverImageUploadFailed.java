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
@EventNameAnnotation(eventName="CoverImageUploadFailed")
public class CoverImageUploadFailed extends AbstractEvent {
	private Long fileId;

    public CoverImageUploadFailed(Long fileId) {
        this.fileId = fileId;
    }
}
