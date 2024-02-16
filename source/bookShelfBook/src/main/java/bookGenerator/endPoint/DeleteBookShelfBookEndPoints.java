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

import bookGenerator._global.event.BookShelfBookDeleted;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;

import bookGenerator.domain.BookShelfBook;
import bookGenerator.domain.BookShelfBookManageService;


@Data
@ToString
class DeleteBookShelfReqDto {
    private Long bookShelfBookId;
}

@Getter
@ToString
class DeleteBookShelfResDto {
    private final Long id;

    public DeleteBookShelfResDto(BookShelfBook bookShelfBook) {
        this.id = bookShelfBook.getId();
    }
}


@RestController
@Transactional
@RequestMapping("/bookShelfBooks")
public class DeleteBookShelfBookEndPoints {

    @PutMapping("/deleteBookShelfBook")
    public ResponseEntity<DeleteBookShelfResDto> deleteBookShelfBook(@RequestBody DeleteBookShelfReqDto reqDto) {
        try {

            CustomLogger.debugObject(CustomLoggerType.ENTER, reqDto);


            BookShelfBook bookShelfBookToDelete = BookShelfBookManageService.getInstance().findByIdOrThrow(reqDto.getBookShelfBookId());
            BookShelfBook.repository().delete(bookShelfBookToDelete);

            (new BookShelfBookDeleted(bookShelfBookToDelete)).publish();
            

            DeleteBookShelfResDto resDto = new DeleteBookShelfResDto(bookShelfBookToDelete);
            CustomLogger.debugObject(CustomLoggerType.EXIT, resDto);
            return ResponseEntity.ok(resDto);

        } catch(Exception e) {
            CustomLogger.errorObject(e, "", reqDto);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
