package bookGenerator.endPoint;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.transaction.Transactional;

import lombok.Data;
import lombok.Getter;
import lombok.ToString;

import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;
import bookGenerator.domain.Book;


@Data
@ToString
class LikeBookReqDto {
    private final Long bookId;
}

@Getter
@ToString
class LikeBookResDto {
    private final Long id;

    public LikeBookResDto(Book book) {
        this.id = book.getId();
    }
}


@RestController
@Transactional
@RequestMapping("/books")
public class LikeBookEndPoints {

    @PutMapping("/likeBook")
    public ResponseEntity<Void> likeBook(@RequestHeader("User-Id") Long userId, LikeBookReqDto reqDto) {
        try {

            CustomLogger.debugObject(CustomLoggerType.ENTER, reqDto);

            // [1] reqDto.bookId로 Book 객체를 찾음

            // [2] BookLiked 이벤트를 찾은 Book 객체와 요청한 userId로 발생시킴

            // [3] 저장한 Book 객체의 ID를 반환
                
            CustomLogger.debug(CustomLoggerType.EXIT);

            return ResponseEntity.status(HttpStatus.OK).build();

        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
