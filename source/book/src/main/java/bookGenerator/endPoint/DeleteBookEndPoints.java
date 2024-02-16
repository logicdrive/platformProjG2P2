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
    public ResponseEntity<DeleteBookResDto> deleteBook(@RequestBody DeleteBookReqDto reqDto) {
        try {

            CustomLogger.debugObject(CustomLoggerType.ENTER, reqDto);
            

            Book bookToDelete = BookManageService.getInstance().findByIdOrThrow(reqDto.getBookId());
            Book.repository().delete(bookToDelete);

            (new BookDeleted(bookToDelete)).publish();
            

            DeleteBookResDto resDto = new DeleteBookResDto(bookToDelete);
            CustomLogger.debugObject(CustomLoggerType.EXIT, resDto);
            return ResponseEntity.ok(resDto);

        } catch(Exception e) {
            CustomLogger.errorObject(e, "", reqDto); 
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
