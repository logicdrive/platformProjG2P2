package bookGenerator._global.eventBase;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import bookGenerator._global.infra.AbstractEvent;

import bookGenerator.domain.User;

@Data
@ToString
@EqualsAndHashCode(callSuper=false)
public class UserEvent extends AbstractEvent {
    protected Long id;
    protected String email;
    protected String password;
    protected String name;
    protected String role;
    protected Date createdDate;
    protected Date updatedDate;

    public UserEvent(User aggregate) {
        super(aggregate);
    }

    public UserEvent() {
        super();
    }
}
