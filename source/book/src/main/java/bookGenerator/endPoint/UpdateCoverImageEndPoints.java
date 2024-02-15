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
import bookGenerator._global.event.CoverImageUpdateRequested;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;
import bookGenerator.domain.Book;
import bookGenerator.domain.BookManageService;


@Data
class UpdateCoverImageeReqDto {
    private Long bookId;
    private String imageUrl;
    
    public String toString() { 
        return String.format("%s(bookId=%s, imageUrlLength=%d)",
            this.getClass().getSimpleName(), this.bookId, this.imageUrl.length());
    }
}

@Getter
@ToString
class UpdateCoverImageResDto {
    private final Long id;

    public UpdateCoverImageResDto(Book book) {
        this.id = book.getId();
    }
}


@RestController
@Transactional
@RequestMapping("/books")
public class UpdateCoverImageEndPoints {
    @PutMapping("/updateCoverImage")
    public ResponseEntity<UpdateCoverImageResDto> updateCoverImage(@RequestBody UpdateCoverImageeReqDto reqDto) {
        try {

            CustomLogger.debugObject(CustomLoggerType.ENTER, reqDto);

            // [1] reqDto.bookId로 Book 객체를 찾음
            Book bookToUpdateCover = BookManageService.getInstance().findByIdOrThrow(reqDto.getBookId());

            // [2] CoverImageUpdateRequested 이벤트를 찾은 Book 객체 및 DataUrl로 발생시킴
            (new CoverImageUpdateRequested(bookToUpdateCover, reqDto.getImageUrl())).publish();;

            // [3] 찾은 Book 객체의 ID를 반환
            UpdateCoverImageResDto resDto = new UpdateCoverImageResDto(bookToUpdateCover);

            CustomLogger.debugObject(CustomLoggerType.EXIT, resDto);

            return ResponseEntity.ok(resDto);

            // Fin

        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
