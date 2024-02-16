package bookGenerator.endPoint;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;

import lombok.Data;
import lombok.ToString;

import bookGenerator._global.event.ProblemGenerationRequested;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;


@Data
@ToString
class GenerateProblemReqDto {
    private Long indexId;
    private String query;
}


@RestController
@Transactional
@RequestMapping("/problems")
public class GenerateProblemEndPoints {

    @PutMapping("/generateProblem")
    public ResponseEntity<Void> generateProblem(@RequestBody GenerateProblemReqDto reqDto) {
        try {

            CustomLogger.debugObject(CustomLoggerType.ENTER, reqDto);
            

            (new ProblemGenerationRequested(reqDto.getIndexId(), reqDto.getQuery())).publish();

            
            CustomLogger.debug(CustomLoggerType.EXIT);

            return ResponseEntity.status(HttpStatus.OK).build();

        } catch(Exception e) {
            CustomLogger.errorObject(e, "", reqDto); 
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
