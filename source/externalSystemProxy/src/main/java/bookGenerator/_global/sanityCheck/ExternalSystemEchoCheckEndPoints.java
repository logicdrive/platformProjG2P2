package bookGenerator._global.sanityCheck;

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
import bookGenerator._global.externalSystemProxy.sanityCheck.echoWithJson.EchoWithJsonReqDto;
import bookGenerator._global.externalSystemProxy.sanityCheck.echoWithJson.EchoWithJsonResDto;
import bookGenerator._global.externalSystemProxy.sanityCheck.echoWithJson.ExternalSystemProxy_EchoWithJson;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;


@Data
@ToString
class ExternalSystemEchoCheckReqDto {
    private String message;
}

@Getter
@ToString
class ExternalSystemEchoCheckResDto {
    private final String message;;

    public ExternalSystemEchoCheckResDto(String message) {
        this.message = message;
    }
}


@RestController
@Transactional
@RequestMapping("/sanityCheck")
public class ExternalSystemEchoCheckEndPoints {
    @PutMapping("/externalSystemEchoCheck")
    public ResponseEntity<ExternalSystemEchoCheckResDto> createComment(@RequestBody ExternalSystemEchoCheckReqDto reqDto) {
        try {

            CustomLogger.debugObject(CustomLoggerType.ENTER, reqDto);


            EchoWithJsonReqDto echoWithJsonReqDto = new EchoWithJsonReqDto(reqDto.getMessage());
            EchoWithJsonResDto echoWithJsonResDto = ExternalSystemProxy_EchoWithJson.getInstance().echoWithJson(echoWithJsonReqDto);


            ExternalSystemEchoCheckResDto resDto = new ExternalSystemEchoCheckResDto(echoWithJsonResDto.getMessage());
            CustomLogger.debugObject(CustomLoggerType.EXIT, resDto);
            return ResponseEntity.ok(resDto);

        } catch(Exception e) {
            CustomLogger.errorObject(e, "", reqDto);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
