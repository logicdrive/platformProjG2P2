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

import bookGenerator._global.event.CoverImageGenerationRequested;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;

import bookGenerator.domain.Book;
import bookGenerator.domain.BookManageService;


@Data
@ToString
class GenerateCoverImageReqDto {
    private Long bookId;
}

@Getter
@ToString
class GenerateCoverImageResDto {
    private final Long id;

    public GenerateCoverImageResDto(Book book) {
        this.id = book.getId();
    }
}


@RestController
@Transactional
@RequestMapping("/books")
public class GenerateCoverImageEndPoints {

    // 유저가 E-Book의 표지를 AI를 통한 생성하기 버튼을 눌렀을 경우, E-Book의 표지를 생성 요청을 보내기 위해서
    @PutMapping("/generateCoverImage")
    public ResponseEntity<GenerateCoverImageResDto> generateCoverImage(@RequestBody GenerateCoverImageReqDto reqDto) {
        try {

            CustomLogger.debugObject(CustomLoggerType.ENTER, reqDto);


            Book bookToGen = BookManageService.getInstance().findByIdOrThrow(reqDto.getBookId());
            (new CoverImageGenerationRequested(bookToGen)).publish();


            GenerateCoverImageResDto resDto = new GenerateCoverImageResDto(bookToGen);
            CustomLogger.debugObject(CustomLoggerType.EXIT, resDto);
            return ResponseEntity.ok(resDto);

        } catch(Exception e) {
            CustomLogger.errorObject(e, "", reqDto); 
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
