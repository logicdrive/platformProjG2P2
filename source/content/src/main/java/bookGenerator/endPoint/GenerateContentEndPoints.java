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

import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;


@Data
@ToString
class GenerateContentReqDto {
    private final Long indexId;
}


@RestController
@Transactional
@RequestMapping("/contents")
public class GenerateContentEndPoints {

    @PutMapping("/generateContent")
    public ResponseEntity<Void> generateContent(@RequestBody GenerateContentReqDto reqDto) {
        try {

            CustomLogger.debug(CustomLoggerType.ENTER, reqDto.toString());

            // [1] ContentImageGenerationRequested 이벤트를 indexId를 기반으로 생성함
                
            CustomLogger.debug(CustomLoggerType.EXIT);

            return ResponseEntity.status(HttpStatus.OK).build();

        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
