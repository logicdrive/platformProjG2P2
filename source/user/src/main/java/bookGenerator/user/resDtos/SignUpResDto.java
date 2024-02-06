package bookGenerator.user.resDtos;

import bookGenerator.domain.User;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class SignUpResDto {
    private final Long id;

    public SignUpResDto(User user) {
        this.id = user.getId();
    }
}
