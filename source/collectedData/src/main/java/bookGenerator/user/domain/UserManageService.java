package bookGenerator.user.domain;

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


    public User findByUserIdOrThrow(Long userId) {
        return User.repository().findByUserId(userId)
            .orElseThrow(UserNotFoundException::new);
    }
}

@Getter
class UserNotFoundException extends CustomException {       
    public UserNotFoundException() {
        super(
            HttpStatus.BAD_REQUEST,
            "UserNotFoundException",
            "해당하는 User(을)를 찾을 수 없습니다.\n" +
            "입력된 정보가 정확한지 확인해주세요."
        );
    }
}