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
import bookGenerator.domain.Book;


@Data
class UpdateCoverImageeReqDto {
    private final Long bookId;
    private final String imageUrl;
    
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
    public ResponseEntity<Void> updateCoverImage(@RequestBody UpdateCoverImageeReqDto reqDto) {
        try {

            CustomLogger.debugObject(CustomLoggerType.ENTER, reqDto);

            // [1] reqDto.bookId로 Book 객체를 찾음

            // [2] CoverImageUpdateRequested 이벤트를 찾은 Book 객체 및 DataUrl로 발생시킴

            // [3] 찾은 Book 객체의 ID를 반환
            
            CustomLogger.debug(CustomLoggerType.EXIT);

            return ResponseEntity.status(HttpStatus.OK).build();

        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
