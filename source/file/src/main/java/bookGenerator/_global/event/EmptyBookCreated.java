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
@EventNameAnnotation(eventName="EmptyBookCreated")
public class EmptyBookCreated extends AbstractEvent {
	private Long id;
	private String createrId;
	private Long coverImageFileid;
	private String title;
	private Boolean isShared;
	private Date createdDate;
	private Date updatedDate;

    public EmptyBookCreated() {
        super();
    }
}
