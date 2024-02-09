package bookGenerator._global.event;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import bookGenerator._global.eventBase.EventNameAnnotation;
import bookGenerator._global.infra.AbstractEvent;

@Data
@ToString
@EqualsAndHashCode(callSuper=false)
@EventNameAnnotation(eventName="ContentImageDeleteRequsted")
public class ContentImageDeleteRequsted extends AbstractEvent {
	private Long id;
	private Long imageFileId;
	private Long indexId;
	private String content;
	private Date createdDate;
	private Date updatedDate;

    public ContentImageDeleteRequsted() {
        super();
    }
}
