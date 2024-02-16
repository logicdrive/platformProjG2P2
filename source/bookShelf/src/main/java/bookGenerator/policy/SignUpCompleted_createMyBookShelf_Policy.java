package bookGenerator.policy;

import javax.transaction.Transactional;

import org.springframework.cloud.stream.annotation.StreamListener;
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


            BookShelf savedBookShelf = BookShelf.repository().save(
                BookShelf.builder()
                    .createrId(signUpCompleted.getId())
                    .title("My BookShelf")
                    .isShared(false)
                    .isDeletable(false)
                    .build()
            );
            (new BookShelfCreated(savedBookShelf)).publish();

            
            CustomLogger.debug(CustomLoggerType.EXIT);

        } catch(Exception e) {
            CustomLogger.errorObject(e, "", signUpCompleted);        
        }
    }

}
