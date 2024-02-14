package bookGenerator.endPoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;

import javax.transaction.Transactional;

import lombok.ToString;
import lombok.Getter;
import bookGenerator._global.event.EmptyBookCreated;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;

import bookGenerator.domain.Book;
import bookGenerator.domain.BookRepository;


@Getter
@ToString
class CreateEmptyBookResDto {
    private final Long id;

    public CreateEmptyBookResDto(Book book) {
        this.id = book.getId();
    }
}


@RestController
@Transactional
@RequestMapping("/books")
public class CreateEmptyBookEndPoints {

    private BookRepository bookRepository;
    @Autowired
    private ApplicationEventPublisher eventPublisher;

    // 유저가 E-Book 생성하기 버튼을 눌렀을 경우, 비어있는 E-Book을 생성하기 위해서
    @PutMapping("/createEmptyBook")
    public ResponseEntity<String> createEmptyBook(@RequestHeader("User-Id") Long userId) {
        try {

            CustomLogger.debug(CustomLoggerType.ENTER, "{userId: " + userId + "}");

            // [1] 새로운 Book 객체를 CreaterId=userId로 생성
            Book book = new Book();
            // [!] createrId만 초기화시키면 되며, 다른 변수들은 자동으로 초기화됨
            book.setCreaterId(userId);
            bookRepository.save(book);
            // [2] EmptyBookCreated 이벤트를 발생시킴
            EmptyBookCreated event = new EmptyBookCreated(book);
            eventPublisher.publishEvent(event);
            // [3] 생성된 Book 객체의 ID를 반환
            Long bookId = book.getId();
            CustomLogger.debug(CustomLoggerType.EXIT);

            return ResponseEntity.status(HttpStatus.OK).body("bookId:" + bookId);

        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
