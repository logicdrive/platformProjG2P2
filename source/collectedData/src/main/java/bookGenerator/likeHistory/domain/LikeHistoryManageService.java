package bookGenerator.likeHistory.domain;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import bookGenerator.BootApplication;
import bookGenerator._global.customExceptionControl.CustomException;

@Service
@RequiredArgsConstructor
public class LikeHistoryManageService {
    public static LikeHistoryManageService getInstance() {
        return BootApplication.applicationContext.getBean(
            LikeHistoryManageService.class
        );
    }

    
    public LikeHistory findByIdOrThrow(Long id) {
        return LikeHistory.repository().findById(id)
            .orElseThrow(() -> new LikeHistoryNotFoundException());
    }
}

@Getter
class LikeHistoryNotFoundException extends CustomException {       
    public LikeHistoryNotFoundException() {
        super(
            HttpStatus.BAD_REQUEST,
            "LikeHistoryNotFoundException",
            "해당하는 LikeHistory(을)를 찾을 수 없습니다.\n" +
            "입력된 정보가 정확한지 확인해주세요."
        );
    }
}