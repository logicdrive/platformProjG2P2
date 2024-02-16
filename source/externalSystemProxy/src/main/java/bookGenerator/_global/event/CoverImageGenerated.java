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
@EventNameAnnotation(eventName="CoverImageGenerated")
public class CoverImageGenerated extends AbstractEvent {
	private Long fileId;
	private String fileUrl;

    public CoverImageGenerated(Long fileId, String fileUrl) {
        this.fileId = fileId;
        this.fileUrl = fileUrl;
    }
}
