package bookGenerator.endPoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
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
import bookGenerator._global.event.BookIsSharedUpdated;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;
import bookGenerator.domain.Book;
import bookGenerator.domain.BookRepository;

@Data
@ToString
class UpdateIsSharedReqDto {
    private Long bookId;
    private Boolean isShared;
}

@Getter
@ToString
class UpdateIsSharedResDto {
    private final Long id;

    public UpdateIsSharedResDto(Book book) {
        this.id = book.getId();
    }
}

@RestController
@Transactional
@RequestMapping("/books")
public class UpdateIsSharedEndPoints {

    @Autowired
    private BookRepository bookRepository;
    private final ApplicationEventPublisher eventPublisher;

    public UpdateIsSharedEndPoints(ApplicationEventPublisher eventPublisher) {
        if (eventPublisher == null) {
            throw new IllegalArgumentException("eventPublisher cannot be null");
        }
        this.eventPublisher = eventPublisher;
    }

    @PutMapping("/updateIsShared")
    public ResponseEntity<UpdateIsSharedResDto> updateIsShared(@RequestBody UpdateIsSharedReqDto reqDto) {
        try {

            if (reqDto == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            CustomLogger.debugObject(CustomLoggerType.ENTER, reqDto);

            // [1] reqDto.bookId로 Book 객체를 찾음
            Book book = bookRepository.findById(reqDto.getBookId())
                    .orElseThrow(() -> new RuntimeException("Book not found"));
                    

            // [2] reqDto.isShared로 Book 객체의 isShared을 변경하고 저장함
            if (book == null) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            }
            book.setShared(reqDto.getIsShared());
            bookRepository.save(book);

            // [3] BookIsSharedUpdated 이벤트를 저장한 Book 객체로 발생시킴
            BookIsSharedUpdated event = new BookIsSharedUpdated(book);
            eventPublisher.publishEvent(event);

            // [4] 저장한 Book 객체의 ID를 반환

            UpdateIsSharedResDto resDto = new UpdateIsSharedResDto(book);

            CustomLogger.debug(CustomLoggerType.EXIT);

            return ResponseEntity.ok(resDto);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
