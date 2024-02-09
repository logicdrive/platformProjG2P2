package bookGenerator.recommenedBookToBook.domain;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import bookGenerator.BootApplication;
import bookGenerator._global.customExceptionControl.CustomException;

@Service
@RequiredArgsConstructor
public class RecommenedBookToBookManageService {
    public static RecommenedBookToBookManageService getInstance() {
        return BootApplication.applicationContext.getBean(
            RecommenedBookToBookManageService.class
        );
    }

    
    public RecommenedBookToBook findByIdOrThrow(Long id) {
        return RecommenedBookToBook.repository().findById(id)
            .orElseThrow(() -> new RecommenedBookToBookNotFoundException());
    }
}

@Getter
class RecommenedBookToBookNotFoundException extends CustomException {       
    public RecommenedBookToBookNotFoundException() {
        super(
            HttpStatus.BAD_REQUEST,
            "RecommenedBookToBookNotFoundException",
            "해당하는 RecommenedBookToBook(을)를 찾을 수 없습니다.\n" +
            "입력된 정보가 정확한지 확인해주세요."
        );
    }
}