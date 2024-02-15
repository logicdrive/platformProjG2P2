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
import bookGenerator.domain.BookShelfRepository;


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
    private BookShelfRepository bookShelfRepository;

    @PutMapping("/createBookShelf")
    public ResponseEntity<CreateBookShelfResDto> createBookShelf(@RequestHeader("User-Id") Long userId, @RequestBody CreateBookShelfReqDto reqDto) {
        try {

            CustomLogger.debugObject(CustomLoggerType.ENTER, reqDto);


            // [1] 새로운 BookShelf 객체를 CreaterId=userId로 생성
            // [!] createrId, title, isShared, isDeletable만 초기화시키면 되며, 다른 변수들은 자동으로 초기화됨
            // [!] isDeletable은 true로 초기화
            BookShelf bookShelfToCreate = BookShelf.repository().save(
                BookShelf.builder()
                .createrId(userId)
                .title(reqDto.getTitle())
                .isShared(reqDto.getIsShared())
                .isDeletable(true)
                .build()
            );

            // [2] BookShelfCreated 이벤트를 발생시킴
            (new BookShelfCreated(bookShelfToCreate)).publish();

            // [3] 생성된 BookShelf 객체의 ID를 반환
            CreateBookShelfResDto resDto = new CreateBookShelfResDto(bookShelfToCreate);
                
            CustomLogger.debug(CustomLoggerType.EXIT);

            return ResponseEntity.ok(resDto);

        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
