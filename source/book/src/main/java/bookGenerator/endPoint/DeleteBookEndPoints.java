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
import bookGenerator._global.event.BookDeleted;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;
import bookGenerator.domain.Book;
import bookGenerator.domain.BookManageService;


@Data
@ToString
class DeleteBookReqDto {
    private Long bookId;
}

@Getter
@ToString
class DeleteBookResDto {
    private final Long id;

    public DeleteBookResDto(Book book) {
        this.id = book.getId();
    }
}


@RestController
@Transactional
@RequestMapping("/books")
public class DeleteBookEndPoints {

    @PutMapping("/deleteBook")
    public ResponseEntity<Long> deleteBook(@RequestBody DeleteBookReqDto reqDto) {
        try {

            CustomLogger.debugObject(CustomLoggerType.ENTER, reqDto);
            
            // [1] reqDto.bookId로 Book 객체를 찾음
            Book book = BookManageService.getInstance().findByIdOrThrow(reqDto.getBookId());

            
            // [2] Book 객체를 삭제
            Book.repository().delete(book);


            // [3] BookDeleted 이벤트를 찾은 Book 객체로 발생시킴
            (new BookDeleted(book)).publish();

            
            // [4] 찾은 Book 객체의 ID를 반환
            // CustomLogger.debug(CustomLoggerType.EXIT);
            DeleteBookResDto deleteBookResDto = new DeleteBookResDto(book);
            CustomLogger.debugObject(CustomLoggerType.EXIT, deleteBookResDto);

            // return ResponseEntity.status(HttpStatus.OK).build();
            return ResponseEntity.status(HttpStatus.OK).body(deleteBookResDto.getId());

        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            
        }
    }

}
