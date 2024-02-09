package [[SERVICE_INFO.PACKAGE_NAME]].endPoint;

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

import [[SERVICE_INFO.PACKAGE_NAME]]._global.logger.CustomLogger;
import [[SERVICE_INFO.PACKAGE_NAME]]._global.logger.CustomLoggerType;

@RestController
@Transactional
@RequestMapping("/[[TEMPLATE.ENTITY_NAME]]s")
public class [[TEMPLATE.ENDPOINT_TITLE]]EndPoints {

    @PutMapping("/[[TEMPLATE.ENDPOINT_FUNCTION]]")
    public ResponseEntity<Void> [[TEMPLATE.ENDPOINT_FUNCTION]]() {
        try {

            CustomLogger.debug(CustomLoggerType.ENTER);
                
            CustomLogger.debug(CustomLoggerType.EXIT);

            return ResponseEntity.status(HttpStatus.OK).build();

        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
