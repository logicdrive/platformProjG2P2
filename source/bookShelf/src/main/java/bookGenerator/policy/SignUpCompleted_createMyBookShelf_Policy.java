package bookGenerator.policy;

import javax.transaction.Transactional;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import bookGenerator._global.config.kafka.KafkaProcessor;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;
import bookGenerator.domain.BookShelf;
import bookGenerator._global.event.BookShelfCreated;
import bookGenerator._global.event.SignUpCompleted;

@Service
@Transactional
public class SignUpCompleted_createMyBookShelf_Policy {

    private final ApplicationEventPublisher eventPublisher;

    public SignUpCompleted_createMyBookShelf_Policy(ApplicationEventPublisher eventPublisher) {
        if (eventPublisher == null) {
            throw new IllegalArgumentException("eventPublisher cannot be null");
        }
        this.eventPublisher = eventPublisher;
    }

    // 유저가 회원가입 했을 경우, 디폴트로 나의 책장을 생성시키는 정책
    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='SignUpCompleted'"
    )
    public void signUpCompleted_createMyBookShelf_Policy(
        @Payload SignUpCompleted signUpCompleted
    ) {
        try
        {
            
            CustomLogger.debugObject(CustomLoggerType.ENTER, signUpCompleted);

            // [1] 새로운 BookShelf 객체를 생성
            // [!] createrId, title, isShared, isDeletable만 초기화시키면 되며, 다른 변수들은 자동으로 초기화됨
            // [!] title="My BookShelf", isShared=false, isDeletable=false로 초기화
            BookShelf newBookShelf = BookShelf.builder()
                    .createrId(signUpCompleted.getId())
                    .title("My BookShelf")
                    .isShared(false)
                    .isDeletable(false)
                    .build();
            newBookShelf = BookShelf.repository().save(newBookShelf);
            

            // [2] BookShelfCreated 이벤트를 발생시킴
            BookShelfCreated event = new BookShelfCreated(newBookShelf);
            eventPublisher.publishEvent(event);

            

            CustomLogger.debug(CustomLoggerType.EXIT);

        } catch(Exception e) {
            CustomLogger.errorObject(e, "", signUpCompleted);        
        }
    }

}
