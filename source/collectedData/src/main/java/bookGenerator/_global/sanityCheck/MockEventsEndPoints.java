package bookGenerator._global.sanityCheck;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Getter;

import bookGenerator._global.customExceptionControl.CustomException;
import bookGenerator._global.infra.AbstractEvent;
import bookGenerator._global.logger.CustomLogger;

// Policy 테스트용으로 이벤트를 강제로 발생시키기 위해서
@RestController
@RequestMapping("/sanityCheck")
public class MockEventsEndPoints {
    @Value("${global.package_name}")
    private String globalPackageName;

    private List<String> packagePathsToSearch = Arrays.asList(
        "user.event", "tag.event", "recommenedBookToBook.event", "recommendBookToUser.event",
        "problem.event", "likeHistory.event", "index.event", "file.event",
        "content.event", "comment.event", "bookShelfBook.event", "bookShelf.event",
        "book.event"
    );

    
    @PostMapping("/mock/{eventName}")
    public void mockEvents(@PathVariable String eventName, @RequestBody String jsonData) {
        try {

            Class<?> eventClass = null;
            for(String packagePath : this.packagePathsToSearch) {
                try {
                    eventClass = Class.forName(String.format("%s.%s.%s", this.globalPackageName, packagePath, eventName));
                    break;
                } catch (Exception e) {}
            }
            if(eventClass == null)
                throw new EventFoundException();

            AbstractEvent event = (AbstractEvent)((new ObjectMapper()).readValue(jsonData, eventClass));
            event.publish();

        } catch (Exception e) {
            CustomLogger.error(e);
        }
    }
}

@Getter
class EventFoundException extends CustomException {       
    public EventFoundException() {
        super(
            HttpStatus.BAD_REQUEST,
            "EventFoundException",
            "해당하는 Event를 찾을 수 없습니다.\n" +
            "입력된 정보가 정확한지 확인해주세요."
        );
    }
}
