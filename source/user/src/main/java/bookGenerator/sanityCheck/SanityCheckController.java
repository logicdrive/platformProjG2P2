package bookGenerator.sanityCheck;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;
import bookGenerator._global.exceptions.DivByZeroException;
import bookGenerator._global.infra.AbstractEvent;

import bookGenerator.sanityCheck.resDtos.AuthenticationCheckResDto;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sanityCheck")
public class SanityCheckController {
    // 정상적인 에러 로그 출력 여부를 테스트해보기 위해서
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

    
    // 게이트웨이 JWT 인증시에 관련 정보를 얻을 수 있는지 테스트해보기 위해서
    @GetMapping("/authenticationCheck")
    public ResponseEntity<AuthenticationCheckResDto> authenticationCheck(@RequestHeader("User-Id") Long userId, @RequestHeader("User-Role") String userRole) {
        AuthenticationCheckResDto authenticationCheckResDto = new AuthenticationCheckResDto(userId, userRole);
        CustomLogger.debug(CustomLoggerType.ENTER_EXIT, "", String.format("{authenticationCheckResDto: %s}", authenticationCheckResDto.toString()));
        return ResponseEntity.ok(authenticationCheckResDto);
    }


    // Policy 테스트용으로 이벤트를 강제로 발생시키기 위해서
    @PostMapping("/mock/{eventName}")
    public void mockEvents(@PathVariable String eventName, @RequestBody String jsonData) {
        try {
            
            Class<?> eventClass = Class.forName(String.format("bookGenerator._global.event.%s", eventName));
            AbstractEvent event = (AbstractEvent)((new ObjectMapper()).readValue(jsonData, eventClass));
            event.publish();

        } catch (Exception e) {
            CustomLogger.error(e);
        }
    }
}
