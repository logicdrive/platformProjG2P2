package bookGenerator.bookShelfBook.domain;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import bookGenerator.BootApplication;
import bookGenerator._global.customExceptionControl.CustomException;

@Service
@RequiredArgsConstructor
public class BookShelfBookManageService {
    public static BookShelfBookManageService getInstance() {
        return BootApplication.applicationContext.getBean(
            BookShelfBookManageService.class
        );
    }

    
    public BookShelfBook findByBookShelfBookId(Long bookShelfBookId) {
        return BookShelfBook.repository().findByBookShelfBookId(bookShelfBookId)
            .orElseThrow(BookShelfBookNotFoundException::new);
    }
}

@Getter
class BookShelfBookNotFoundException extends CustomException {       
    public BookShelfBookNotFoundException() {
        super(
            HttpStatus.BAD_REQUEST,
            "BookShelfBookNotFoundException",
            "해당하는 BookShelfBook(을)를 찾을 수 없습니다.\n" +
            "입력된 정보가 정확한지 확인해주세요."
        );
    }
}