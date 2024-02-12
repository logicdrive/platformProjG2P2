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

import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;
import bookGenerator.domain.Book;


@Data
@ToString
class UpdateBookTitleReqDto {
    private Long bookId;
    private String bookTitle;
}

@Getter
@ToString
class UpdateBookTitleResDto {
    private final Long id;

    public UpdateBookTitleResDto(Book book) {
        this.id = book.getId();
    }
}


@RestController
@Transactional
@RequestMapping("/books")
public class UpdateBookTitleEndPoints {

    @PutMapping("/updateBookTitle")
    public ResponseEntity<Void> updateBookTitle(@RequestBody UpdateBookTitleReqDto reqDto) {
        try {

            CustomLogger.debugObject(CustomLoggerType.ENTER, reqDto);

            // [1] reqDto.bookId로 Book 객체를 찾음

            // [2] reqDto.bookTitle로 Book 객체의 제목을 변경하고 저장함

            // [3] BookTitleUpdated 이벤트를 저장한 Book 객체로 발생시킴

            // [4] 저장한 Book 객체의 ID를 반환
                
            CustomLogger.debug(CustomLoggerType.EXIT);

            return ResponseEntity.status(HttpStatus.OK).build();

        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
