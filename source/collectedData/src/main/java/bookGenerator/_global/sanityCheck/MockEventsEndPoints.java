package bookGenerator._global.sanityCheck;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.fasterxml.jackson.databind.ObjectMapper;

import bookGenerator._global.infra.AbstractEvent;
import bookGenerator._global.logger.CustomLogger;


// Policy 테스트용으로 이벤트를 강제로 발생시키기 위해서
@RestController
@RequestMapping("/sanityCheck")
public class MockEventsEndPoints {
    @Value("${global.package_name}")
    private String globalPackageName;

    private Map<String, String> eventPackageMap = new HashMap<String, String>() {{
        put("SignUpCompleted", "user.event");
    }};
    

    @PostMapping("/mock/{eventName}")
    public void mockEvents(@PathVariable String eventName, @RequestBody String jsonData) {
        try {

            Class<?> eventClass = Class.forName(String.format("%s.%s.%s", this.globalPackageName, this.eventPackageMap.get(eventName), eventName));
            AbstractEvent event = (AbstractEvent)((new ObjectMapper()).readValue(jsonData, eventClass));
            event.publish();

        } catch (Exception e) {
            CustomLogger.error(e);
        }
    }
}
