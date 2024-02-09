package bookGenerator.endPoint;

import org.springframework.http.HttpHeaders;
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

@RestController
@Transactional
@RequestMapping("/books")
public class GenerateCoverImageEndPoints {

    @PutMapping("/generateCoverImage")
    public ResponseEntity<Void> generateCoverImage() {
        try {

            CustomLogger.debug(CustomLoggerType.ENTER);
                
            CustomLogger.debug(CustomLoggerType.EXIT);

            return ResponseEntity.status(HttpStatus.OK).build();

        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
