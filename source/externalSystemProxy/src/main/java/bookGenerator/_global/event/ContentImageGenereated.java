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
@EventNameAnnotation(eventName="ContentImageGenereated")
public class ContentImageGenereated extends AbstractEvent {
	private Long contentId;
	private String fileUrl;

    public ContentImageGenereated(Long contentId, String fileUrl) {
        this.contentId = contentId;
        this.fileUrl = fileUrl;
    }
}
