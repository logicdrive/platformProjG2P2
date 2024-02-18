package bookGenerator.recommenedBookToBook.endPoint;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

import javax.transaction.Transactional;

import lombok.Data;
import lombok.Getter;
import lombok.ToString;

import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;
import bookGenerator.recommenedBookToBook.domain.RecommenedBookToBook;


@Data
@ToString
class RecreateReqDto {
    private Long bookId;
    private List<Long> recommendedBookIds;
    private List<Long> priorities;
}

@Getter
@ToString
class RecreateResDto {
    private final Long bookId;

    public RecreateResDto(Long bookId) {
        this.bookId = bookId;
    }
}


@RestController
@Transactional
@RequestMapping("/recommenedBookToBooks")
public class RecreateRecommenedBookToBookEndPoints {
    @PutMapping("/recreate")
    public ResponseEntity<RecreateResDto> recreate(@RequestBody RecreateReqDto reqDto) {
        try {

            CustomLogger.debugObject(CustomLoggerType.ENTER, reqDto);


            RecommenedBookToBook.repository().deleteByBookId(reqDto.getBookId());

            for(int i = 0; i < reqDto.getRecommendedBookIds().size(); i++) {
                RecommenedBookToBook.repository().save(
                    RecommenedBookToBook.builder()
                        .bookId(reqDto.getBookId())
                        .recommendedBookId(reqDto.getRecommendedBookIds().get(i))
                        .priority(reqDto.getPriorities().get(i))
                        .build()
                );
            }


            RecreateResDto resDto = new RecreateResDto(reqDto.getBookId());
            CustomLogger.debugObject(CustomLoggerType.EXIT, resDto);
            return ResponseEntity.ok(resDto);

        } catch(Exception e) {
            CustomLogger.errorObject(e, "", reqDto);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
