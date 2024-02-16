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


            Book bookToUpdate = BookManageService.getInstance().findByIdOrThrow(reqDto.getBookId());
            (new CoverImageUpdateRequested(bookToUpdate, reqDto.getImageUrl())).publish();


            UpdateCoverImageResDto resDto = new UpdateCoverImageResDto(bookToUpdate);
            CustomLogger.debugObject(CustomLoggerType.EXIT, resDto);
            return ResponseEntity.ok(resDto);

        } catch(Exception e) {
            CustomLogger.errorObject(e, "", reqDto); 
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
