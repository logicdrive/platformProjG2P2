package bookGenerator.endPoint;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;

import javax.transaction.Transactional;

import lombok.Data;
import lombok.ToString;

import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;
import bookGenerator._global.security.JwtTokenService;


@Data
@ToString
class SignInReqDto {
    private String email;
    private String password;
}


@RestController
@Transactional
@RequestMapping("/users")
public class SignInEndPoints {

    @PutMapping("/signIn")
    public ResponseEntity<String> signIn(@RequestBody SignInReqDto signInReqDtoForToken) {
        try {

            CustomLogger.debugObject(CustomLoggerType.ENTER, signInReqDtoForToken);


            String jwtToken = JwtTokenService.getInstance().tokenValue(
                signInReqDtoForToken.getEmail(), signInReqDtoForToken.getPassword());
            
                
            CustomLogger.debug(CustomLoggerType.EXIT, "", String.format("{jwtToken: %s}", jwtToken));
            return ResponseEntity.ok()
            .header(HttpHeaders.AUTHORIZATION, jwtToken)
            .build();

        } catch(Exception e) {
            CustomLogger.errorObject(e, signInReqDtoForToken);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
