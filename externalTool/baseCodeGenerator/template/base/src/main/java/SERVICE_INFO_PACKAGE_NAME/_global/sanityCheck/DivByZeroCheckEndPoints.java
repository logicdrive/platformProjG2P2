package [[SERVICE_INFO.PACKAGE_NAME]]._global.sanityCheck;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.Getter;

import [[SERVICE_INFO.PACKAGE_NAME]]._global.customExceptionControl.CustomException;
import [[SERVICE_INFO.PACKAGE_NAME]]._global.logger.CustomLogger;

// 정상적인 에러 로그 출력 여부를 테스트해보기 위해서
@RestController
@RequestMapping("/sanityCheck")
public class DivByZeroCheckEndPoints {

    @GetMapping("/divByZeroCheck")
    public ResponseEntity<Integer> divByZeroCheck() {
        try {

            Integer returnNum = 1/0;
            return ResponseEntity.ok(returnNum);
            
        } catch(Exception e) {
            CustomLogger.error(e, "Div By Zero Check Message", String.format("{returnNum: %s}", "Undefined"));
            throw new DivByZeroException();
        }    
    }

}

@Getter
class DivByZeroException extends CustomException {
    public DivByZeroException() {
        super(
            HttpStatus.INTERNAL_SERVER_ERROR,
            "SanityCheck_DivByZeroException",
            "0으로 나눠서 예외가 발생했습니다.(SanityCheck 용도)"
        );
    }
}