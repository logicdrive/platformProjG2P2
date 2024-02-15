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
import bookGenerator._global.event.BookShelfDeleted;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;
import bookGenerator.domain.BookShelf;
import bookGenerator.domain.BookShelfManageService;


@Data
@ToString
class DeleteBookShelfReqDto {
    private Long bookShelfId;
}

@Getter
@ToString
class DeleteBookShelfResDto {
    private final Long id;

    public DeleteBookShelfResDto(BookShelf bookShelf) {
        this.id = bookShelf.getId();
    }
}


@RestController
@Transactional
@RequestMapping("/bookShelfs")
public class DeleteBookShelfEndPoints {

    @PutMapping("/deleteBookShelf")
    public ResponseEntity<DeleteBookShelfResDto> deleteBookShelf(@RequestBody DeleteBookShelfReqDto reqDto) {
        try {
            CustomLogger.debugObject(CustomLoggerType.ENTER, reqDto);

            // [1] reqDto.bookShelfId로 BookShelf 객체를 찾음
            BookShelf bookShelfToDelete = BookShelfManageService.getInstance().findByIdOrThrow(reqDto.getBookShelfId());

            // [2] BookShelf 객체를 삭제
            BookShelf.repository().delete(bookShelfToDelete);

            // [3] BookShelfDeleted 이벤트를 찾은 BookShelf 객체로 발생시킴
            (new BookShelfDeleted(bookShelfToDelete)).publish();            

            // [4] 찾은 BookShelf 객체의 ID를 반환
            DeleteBookShelfResDto resDto = new DeleteBookShelfResDto(bookShelfToDelete);
            
            CustomLogger.debugObject(CustomLoggerType.EXIT, resDto);

            return ResponseEntity.ok(resDto);

            // Fin

        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
