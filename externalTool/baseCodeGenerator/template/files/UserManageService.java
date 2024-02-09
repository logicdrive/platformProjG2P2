package bookGenerator.domain;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import bookGenerator.BootApplication;
import bookGenerator._global.customExceptionControl.CustomException;

@Service
@RequiredArgsConstructor
public class UserManageService {
    public static UserManageService getInstance() {
        return BootApplication.applicationContext.getBean(
            UserManageService.class
        );
    }

    
    public User findByIdOrThrow(Long id) {
        return User.repository().findById(id)
            .orElseThrow(() -> new UserNotFoundException());
    }

    public User findByEmailOrThrow(String email) {
        return User.repository().findByEmail(email)
            .orElseThrow(() -> new UserNotFoundException());
    }
}

@Getter
class UserNotFoundException extends CustomException {       
    public UserNotFoundException() {
        super(
            HttpStatus.BAD_REQUEST,
            "UserNotFoundException",
            "해당하는 정보의 사용자를 찾을 수 없습니다.\n" +
            "유효한 이메일, 비밀번호인지 확인하거나 계정이 없을 경우 회원가입을 해주세요."
        );
    }
}