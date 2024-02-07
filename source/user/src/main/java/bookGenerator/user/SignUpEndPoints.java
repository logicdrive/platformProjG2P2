package bookGenerator.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import javax.transaction.Transactional;

import bookGenerator._global.event.SignUpCompleted;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;

import bookGenerator.domain.User;


@Data
@ToString
class SignUpReqDto {
    private String email;
    private String password;
    private String name;
}

@Getter
@ToString
class SignUpResDto {
    private final Long id;

    public SignUpResDto(User user) {
        this.id = user.getId();
    }
}


@RestController
@Transactional
@RequiredArgsConstructor
@RequestMapping("/users")
public class SignUpEndPoints {
    private final PasswordEncoder passwordEncoder;


    @PutMapping("/signUp")
    public ResponseEntity<SignUpResDto> signUp(@RequestBody SignUpReqDto signUpReqDto) {
        try {
            CustomLogger.debugObject(CustomLoggerType.ENTER, signUpReqDto);
        

            User savedUser = User.repository().save(
                    User.builder()
                        .email(signUpReqDto.getEmail())
                        .password(this.passwordEncoder.encode(signUpReqDto.getPassword()))
                        .name(signUpReqDto.getName())
                        .role("User")
                        .build()
                );

            (new SignUpCompleted(savedUser)).publish();
            SignUpResDto signUpResDto = new SignUpResDto(savedUser);
        

            CustomLogger.debugObject(CustomLoggerType.EXIT, signUpResDto);
            return ResponseEntity.ok(signUpResDto);

        } catch(Exception e) {
            CustomLogger.errorObject(e, signUpReqDto);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
