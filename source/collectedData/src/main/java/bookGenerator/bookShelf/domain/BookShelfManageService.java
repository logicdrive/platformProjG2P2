package bookGenerator.bookShelf.domain;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import bookGenerator.BootApplication;
import bookGenerator._global.customExceptionControl.CustomException;

@Service
@RequiredArgsConstructor
public class BookShelfManageService {
    public static BookShelfManageService getInstance() {
        return BootApplication.applicationContext.getBean(
            BookShelfManageService.class
        );
    }

    
    public BookShelf findByBookShelfId(Long bookShelfId) {
        return BookShelf.repository().findByBookShelfId(bookShelfId)
            .orElseThrow(BookShelfNotFoundException::new);
    }
}

@Getter
class BookShelfNotFoundException extends CustomException {       
    public BookShelfNotFoundException() {
        super(
            HttpStatus.BAD_REQUEST,
            "BookShelfNotFoundException",
            "해당하는 BookShelf(을)를 찾을 수 없습니다.\n" +
            "입력된 정보가 정확한지 확인해주세요."
        );
    }
}