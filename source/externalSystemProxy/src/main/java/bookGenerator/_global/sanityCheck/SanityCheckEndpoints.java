package bookGenerator._global.sanityCheck;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;

// 프로그램의 정상 통신 여부를 확인해보기 위해서
@RestController
@RequestMapping("/sanityCheck")
public class SanityCheckEndpoints {
    private boolean isNormalSanityCheck = true;

    
    @GetMapping
    public ResponseEntity<Void> sanityCheck() {
        CustomLogger.debug(CustomLoggerType.ENTER_EXIT, "",
            String.format("{isNormalSanityCheck: %b}", this.isNormalSanityCheck));
        
        return ResponseEntity.status((this.isNormalSanityCheck ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR))
            .build();
    }

    // 에러 복구 여부 테스트를 위해서 SanityCheck시에 무조건 정상 처리가 반환되도록 만듬
    @PutMapping("/setNormal")
    public void sanityCheckSetNormal() {
        this.isNormalSanityCheck = true;
        CustomLogger.debug(CustomLoggerType.ENTER_EXIT);
        return;
    }

    // 에러 복구 여부 테스트를 위해서 SanityCheck시에 무조건 내부 서버 에러가 반환되도록 만듬
    @PutMapping("/setError")
    public void sanityCheckSetError() {
        this.isNormalSanityCheck = false;
        CustomLogger.debug(CustomLoggerType.ENTER_EXIT);
        return;
    }
}
