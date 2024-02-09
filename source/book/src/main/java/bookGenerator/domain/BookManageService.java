package bookGenerator.domain;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import bookGenerator.BootApplication;
import bookGenerator._global.customExceptionControl.CustomException;

@Service
@RequiredArgsConstructor
public class BookManageService {
    public static BookManageService getInstance() {
        return BootApplication.applicationContext.getBean(
            BookManageService.class
        );
    }

    
    public Book findByIdOrThrow(Long id) {
        return Book.repository().findById(id)
            .orElseThrow(() -> new BookNotFoundException());
    }
}

@Getter
class BookNotFoundException extends CustomException {       
    public BookNotFoundException() {
        super(
            HttpStatus.BAD_REQUEST,
            "BookNotFoundException",
            "해당하는 Book(을)를 찾을 수 없습니다.\n" +
            "입력된 정보가 정확한지 확인해주세요."
        );
    }
}