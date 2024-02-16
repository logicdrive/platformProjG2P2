package bookGenerator.endPoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;

import javax.transaction.Transactional;

import lombok.Data;
import lombok.Getter;
import lombok.ToString;
import bookGenerator._global.event.BookShelfBookAdded;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;
import bookGenerator.domain.BookShelfBook;


@Data
@ToString
class AddBookShelfBookReqDto {
    private Long bookShelfId;
    private Long bookId;
}

@Getter
@ToString
class AddBookShelfBookResDto {
    private final Long id;

    public AddBookShelfBookResDto(BookShelfBook bookShelfBook) {
        this.id = bookShelfBook.getId();
    }
}


@RestController
@Transactional
@RequestMapping("/bookShelfBooks")
public class AddBookShelfBookEndPoints {

    @PutMapping("/addBookShelfBook")
    public ResponseEntity<AddBookShelfBookResDto> addBookShelfBook(@RequestBody AddBookShelfBookReqDto reqDto) {
        try {

            CustomLogger.debugObject(CustomLoggerType.ENTER, reqDto);

            // [1] 새로운 BookShelfBook 객체를 생성
            // [!] bookShelfId, bookId만 초기화시키면 되며, 다른 변수들은 자동으로 초기화됨
            BookShelfBook bookShel = BookShelfBook.repository().save(
                BookShelfBook.builder()
                .bookShelfId(reqDto.getBookShelfId())
                .bookId(reqDto.getBookId())
                .build()
            );

            // [2] BookShelfBookAdded 이벤트를 생성된 BookShelfBook로 발생시킴
            BookShelfBook shelfBook = BookShelfBook.repository().save(bookShel);

            (new BookShelfBookAdded(shelfBook)).publish();

            // [3] 생성된 BookShelfBook 객체의 ID를 반환
            AddBookShelfBookResDto responseDto = new AddBookShelfBookResDto(shelfBook);

            CustomLogger.debug(CustomLoggerType.EXIT);

            return ResponseEntity.ok().body(responseDto);

        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
