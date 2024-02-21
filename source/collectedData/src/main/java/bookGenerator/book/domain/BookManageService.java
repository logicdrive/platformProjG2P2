package bookGenerator.book.domain;

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

    
    public Book findByBookId(Long bookId) {
        return Book.repository().findByBookId(bookId)
            .orElseThrow(BookNotFoundException::new);
    }

    public Book findByCoverImageFileId(Long coverImageFileId) {
        return Book.repository().findByCoverImageFileId(coverImageFileId)
            .orElseThrow(BookNotFoundException::new);
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