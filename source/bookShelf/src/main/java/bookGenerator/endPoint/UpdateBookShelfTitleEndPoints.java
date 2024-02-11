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
import bookGenerator.domain.BookShelf;


@Data
@ToString
class UpdateBookShelfTitleReqDto {
    private final Long bookShelfId;
    private final String bookShelfTitle;
}

@Getter
@ToString
class UpdateBookShelfTitleResDto {
    private final Long id;

    public UpdateBookShelfTitleResDto(BookShelf bookShelf) {
        this.id = bookShelf.getId();
    }
}


@RestController
@Transactional
@RequestMapping("/bookShelfs")
public class UpdateBookShelfTitleEndPoints {

    @PutMapping("/updateBookShelfTitle")
    public ResponseEntity<Void> updateBookShelfTitle(@RequestBody UpdateBookShelfTitleReqDto reqDto) {
        try {

            CustomLogger.debugObject(CustomLoggerType.ENTER, reqDto);

            // [1] reqDto.bookShelfId로 BookShelf 객체를 찾음

            // [2] reqDto.bookShelfTitle로 BookShelf 객체의 제목을 변경하고 저장함

            // [3] BookShelfTitleUpdated 이벤트를 저장한 BookShelf 객체로 발생시킴

            // [4] 저장한 BookShelf 객체의 ID를 반환
                
            CustomLogger.debug(CustomLoggerType.EXIT);

            return ResponseEntity.status(HttpStatus.OK).build();

        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
