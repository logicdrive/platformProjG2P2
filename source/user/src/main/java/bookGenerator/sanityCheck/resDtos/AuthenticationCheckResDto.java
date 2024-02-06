package bookGenerator.sanityCheck.resDtos;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class AuthenticationCheckResDto {
    private final Long userId;
    private final String userRole;

    public AuthenticationCheckResDto(Long userId, String userRole) {
        this.userId = userId;
        this.userRole = userRole;
    }
}

