package bookGenerator.policy;

import javax.transaction.Transactional;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import bookGenerator._global.config.kafka.KafkaProcessor;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;
import bookGenerator._global.event.BookDeleted;

@Service
@Transactional
public class BookDeleted_deleteTag_Policy {

    // 책이 삭제되었을 경우, 부여된 태그들을 삭제하기 위한 정책
    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='BookDeleted'"
    )
    public void bookDeleted_deleteTag_Policy(
        @Payload BookDeleted bookDeleted
    ) {
        try
        {
            CustomLogger.debugObject(CustomLoggerType.ENTER, bookDeleted);

            // [1] 해당 책에 부여된 태그들을 삭제

            // [2] 각각의 삭제된 태그들에 대해서 TagDeleted 이벤트를 발생시킴

            CustomLogger.debug(CustomLoggerType.EXIT);

        } catch(Exception e) {
            CustomLogger.errorObject(e, "", bookDeleted);        
        }
    }

}
