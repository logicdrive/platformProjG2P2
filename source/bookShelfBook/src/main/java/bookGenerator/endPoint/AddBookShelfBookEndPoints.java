package bookGenerator.endPoint;

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


            BookShelfBook savedBookShelfBook = BookShelfBook.repository().save(
                BookShelfBook.builder()
                    .bookShelfId(reqDto.getBookShelfId())
                    .bookId(reqDto.getBookId())
                    .build()
            );
            (new BookShelfBookAdded(savedBookShelfBook)).publish();


            AddBookShelfBookResDto resDto = new AddBookShelfBookResDto(savedBookShelfBook);
            CustomLogger.debugObject(CustomLoggerType.EXIT, resDto);
            return ResponseEntity.ok(resDto);

        } catch(Exception e) {
            CustomLogger.errorObject(e, "", reqDto);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
