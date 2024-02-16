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

import bookGenerator._global.event.IndexGenerationRequested;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;


@Data
@ToString
class GenerateIndexesReqDto {
    private Long bookId;
    private String query;
}


@RestController
@Transactional
@RequestMapping("/indexes")
public class GenerateIndexesEndPoints {

    @PutMapping("/generateIndexes")
    public ResponseEntity<Void> generateIndexes(@RequestBody GenerateIndexesReqDto reqDto) {
        try {

            CustomLogger.debugObject(CustomLoggerType.ENTER, reqDto);


            (new IndexGenerationRequested(reqDto.getBookId(), reqDto.getQuery())).publish();
                
            
            CustomLogger.debug(CustomLoggerType.EXIT);
            return ResponseEntity.status(HttpStatus.OK).build();

        } catch(Exception e) {
            CustomLogger.errorObject(e, "", reqDto); 
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
