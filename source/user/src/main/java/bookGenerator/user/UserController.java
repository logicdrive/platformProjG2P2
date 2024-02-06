package bookGenerator.user;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;
import bookGenerator.user.reqDtos.SignInReqDto;
import bookGenerator.user.reqDtos.SignUpReqDto;
import bookGenerator.user.reqDtos.UpdateNameReqDto;
import bookGenerator.user.resDtos.SignUpResDto;
import bookGenerator.user.service.UserService;

import org.springframework.web.bind.annotation.PutMapping;

import javax.transaction.Transactional;

import lombok.RequiredArgsConstructor;


@RestController
@Transactional
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @PutMapping("/signUp")
    public ResponseEntity<SignUpResDto> signUp(@RequestBody SignUpReqDto signUpReqDto) {
        try {

            CustomLogger.debug(CustomLoggerType.ENTER, "", String.format("{signUpReqDto: %s}", signUpReqDto.toString()));
        
            SignUpResDto signUpResDto = new SignUpResDto(this.userService.signUp(signUpReqDto));
        
            CustomLogger.debug(CustomLoggerType.EXIT, "", String.format("{signUpResDto: %s}", signUpResDto.toString()));
            return ResponseEntity.ok(signUpResDto);

        } catch(Exception e) {
            CustomLogger.error(e, "", String.format("{signUpReqDto: %s}", signUpReqDto.toString()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/signIn")
    public ResponseEntity<String> signIn(@RequestBody SignInReqDto signInReqDtoForToken) {
        try {

            CustomLogger.debug(CustomLoggerType.ENTER, "", String.format("{signInReqDtoForToken: %s}", signInReqDtoForToken.toString()));

            String jwtToken = this.userService.tokenBySignIn(signInReqDtoForToken);
            
            CustomLogger.debug(CustomLoggerType.EXIT, "", String.format("{jwtToken: %s}", jwtToken));
            return ResponseEntity.ok()
            .header(HttpHeaders.AUTHORIZATION, jwtToken)
            .build();

        } catch(Exception e) {
            CustomLogger.error(e, "", String.format("{signInReqDtoForToken: %s}", signInReqDtoForToken.toString()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    
    @PutMapping("/updateName")
    public ResponseEntity<Void> updateName(@RequestHeader("User-Id") Long userId, @RequestBody UpdateNameReqDto updateNameReqDto) {
        try {

            CustomLogger.debug(CustomLoggerType.ENTER, "", String.format("{updateNameReqDto: %s}", updateNameReqDto.toString()));

            this.userService.updateName(userId, updateNameReqDto);

            return ResponseEntity.status(HttpStatus.OK).build();

        } catch(Exception e) {
            CustomLogger.error(e, "", String.format("{updateNameReqDto: %s}", updateNameReqDto.toString()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
