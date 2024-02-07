package bookGenerator._global.sanityCheck;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.http.ResponseEntity;

import lombok.Getter;
import lombok.ToString;

import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;


@Getter
@ToString
class AuthenticationCheckResDto {
    private final Long userId;
    private final String userRole;

    public AuthenticationCheckResDto(Long userId, String userRole) {
        this.userId = userId;
        this.userRole = userRole;
    }
}


// 게이트웨이 JWT 인증시에 관련 정보를 얻을 수 있는지 테스트해보기 위해서
@RestController
@RequestMapping("/sanityCheck")
public class AuthenticationCheckEndPoints {

    @GetMapping("/authenticationCheck")
    public ResponseEntity<AuthenticationCheckResDto> authenticationCheck(@RequestHeader("User-Id") Long userId, @RequestHeader("User-Role") String userRole) {
        AuthenticationCheckResDto authenticationCheckResDto = new AuthenticationCheckResDto(userId, userRole);
        CustomLogger.debug(CustomLoggerType.ENTER_EXIT, "", String.format("{%s: %s}", authenticationCheckResDto.getClass().getSimpleName(), authenticationCheckResDto.toString()));
        return ResponseEntity.ok(authenticationCheckResDto);
    }

}
