package bookGenerator.endPoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.transaction.Transactional;

import lombok.Data;
import lombok.Getter;
import lombok.ToString;
import bookGenerator._global.event.BookLiked;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;
import bookGenerator.domain.Book;
import bookGenerator.domain.BookManageService;
import bookGenerator.domain.BookRepository;


@Data
@ToString
class LikeBookReqDto {
    private Long bookId;
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

    @Autowired
    private BookRepository bookRepository;
    private final ApplicationEventPublisher eventPublisher;

    public LikeBookEndPoints(ApplicationEventPublisher eventPublisher) {
        if (eventPublisher == null) {
            throw new IllegalArgumentException("eventPublisher cannot be null");
        }
        this.eventPublisher = eventPublisher;
    }
    
    @PutMapping("/likeBook")
    public ResponseEntity<LikeBookResDto> likeBook(@RequestHeader("User-Id") Long userId, @RequestBody LikeBookReqDto reqDto) {
        try {

            CustomLogger.debugObject(CustomLoggerType.ENTER, reqDto);

            // [1] reqDto.bookId로 Book 객체를 찾음
            Book book = BookManageService.getInstance().findByIdOrThrow(reqDto.getBookId());

            // [2] BookLiked 이벤트를 찾은 Book 객체와 요청한 userId로 발생시킴
            
            (new BookLiked(book, userId)).publish();

            // [3] 저장한 Book 객체의 ID를 반환
                
            LikeBookResDto resDto = new LikeBookResDto(book);

            CustomLogger.debug(CustomLoggerType.EXIT);

            return ResponseEntity.ok(resDto);
            
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
