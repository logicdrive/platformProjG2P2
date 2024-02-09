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
@EventNameAnnotation(eventName="SignUpCompleted")
public class SignUpCompleted extends AbstractEvent {
	private Long id;
	private String email;
	private String password;
	private String name;
	private String role;
	private Date createdDate;
	private Date updatedDate;

    public SignUpCompleted() {
        super();
    }
}
