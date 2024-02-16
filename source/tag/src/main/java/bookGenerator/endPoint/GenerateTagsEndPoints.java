package bookGenerator.endPoint;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;

import javax.transaction.Transactional;

import lombok.Data;
import lombok.ToString;

import bookGenerator._global.event.TagGenerationRequested;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;


@Data
@ToString
class GenerateTagsReqDto {
    private Long bookId;
    private String query;
}


@RestController
@Transactional
@RequestMapping("/tags")
public class GenerateTagsEndPoints {

    @PutMapping("/generateTags")
    public ResponseEntity<Void> generateTags(@RequestBody GenerateTagsReqDto reqDto) {
        try {

            CustomLogger.debugObject(CustomLoggerType.ENTER, reqDto);


            (new TagGenerationRequested(reqDto.getBookId(), reqDto.getQuery())).publish();


            CustomLogger.debug(CustomLoggerType.EXIT);
            return ResponseEntity.status(HttpStatus.OK).build();

        } catch(Exception e) {
            CustomLogger.errorObject(e, "", reqDto); 
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
