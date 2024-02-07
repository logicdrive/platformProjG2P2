package bookGenerator._global.event;

import java.util.Date;

import bookGenerator._global.infra.AbstractEvent;
import bookGenerator.domain.User;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

// 유저 이름이 업데이트 되었음을 알리기 위한 이벤트
@Data
@ToString
@EqualsAndHashCode(callSuper=false)
public class UserNameUpdated extends AbstractEvent {
    private Long id;
    private String email;
    private String password;
    private String name;
    private String role;
    private Date createdDate;
    private Date updatedDate;

    public UserNameUpdated(User aggregate) {
        super(aggregate);
    }

    public UserNameUpdated() {
        super();
    }
}
