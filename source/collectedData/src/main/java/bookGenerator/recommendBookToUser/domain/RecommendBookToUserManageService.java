package bookGenerator.recommendBookToUser.domain;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import bookGenerator.BootApplication;
import bookGenerator._global.customExceptionControl.CustomException;

@Service
@RequiredArgsConstructor
public class RecommendBookToUserManageService {
    public static RecommendBookToUserManageService getInstance() {
        return BootApplication.applicationContext.getBean(
            RecommendBookToUserManageService.class
        );
    }
}

@Getter
class RecommendBookToUserNotFoundException extends CustomException {       
    public RecommendBookToUserNotFoundException() {
        super(
            HttpStatus.BAD_REQUEST,
            "RecommendBookToUserNotFoundException",
            "해당하는 RecommendBookToUser(을)를 찾을 수 없습니다.\n" +
            "입력된 정보가 정확한지 확인해주세요."
        );
    }
}