package bookGenerator.endPoint;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;

import javax.transaction.Transactional;

import lombok.Data;
import lombok.Getter;
import lombok.ToString;

import bookGenerator._global.event.BookShelfCreated;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;

import bookGenerator.domain.BookShelf;


@Data
@ToString
class CreateBookShelfReqDto {
    private String title;
    private Boolean isShared;
}

@Getter
@ToString
class CreateBookShelfResDto {
    private final Long id;

    public CreateBookShelfResDto(BookShelf bookShelf) {
        this.id = bookShelf.getId();
    }
}


@RestController
@Transactional
@RequestMapping("/bookShelfs")
public class CreateBookShelfEndPoints {

    @PutMapping("/createBookShelf")
    public ResponseEntity<CreateBookShelfResDto> createBookShelf(@RequestHeader("User-Id") Long userId, @RequestBody CreateBookShelfReqDto reqDto) {
        try {

            CustomLogger.debugObject(CustomLoggerType.ENTER, reqDto);


            BookShelf savedBookShelf = BookShelf.repository().save(
                BookShelf.builder()
                    .createrId(userId)
                    .title(reqDto.getTitle())
                    .isShared(reqDto.getIsShared())
                    .isDeletable(true)
                    .build()
            );

            (new BookShelfCreated(savedBookShelf)).publish();


            CreateBookShelfResDto resDto = new CreateBookShelfResDto(savedBookShelf);
            CustomLogger.debugObject(CustomLoggerType.EXIT, resDto);
            return ResponseEntity.ok(resDto);

        } catch(Exception e) {
            CustomLogger.errorObject(e, "", reqDto);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
