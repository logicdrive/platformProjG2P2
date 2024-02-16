package bookGenerator.endPoint;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;

import javax.transaction.Transactional;

import lombok.ToString;
import lombok.Getter;

import bookGenerator._global.event.EmptyBookCreated;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;

import bookGenerator.domain.Book;


@Getter
@ToString
class CreateEmptyBookResDto {
    private final Long id;

    public CreateEmptyBookResDto(Book book) {
        this.id = book.getId();
    }
}


@RestController
@Transactional
@RequestMapping("/books")
public class CreateEmptyBookEndPoints {

    // 유저가 E-Book 생성하기 버튼을 눌렀을 경우, 비어있는 E-Book을 생성하기 위해서
    @PutMapping("/createEmptyBook")
    public ResponseEntity<CreateEmptyBookResDto> createEmptyBook(@RequestHeader("User-Id") Long userId) {
        try {

            CustomLogger.debug(CustomLoggerType.ENTER, "{userId: " + userId + "}");


            Book savedBook = Book.repository().save(
                Book.builder()
                    .createrId(userId)
                    .build()
            );
            (new EmptyBookCreated(savedBook)).publish();

            
            CreateEmptyBookResDto resDto = new CreateEmptyBookResDto(savedBook);
            CustomLogger.debugObject(CustomLoggerType.EXIT, resDto);
            return ResponseEntity.ok(resDto);

        } catch(Exception e) {
            CustomLogger.error(e); 
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
