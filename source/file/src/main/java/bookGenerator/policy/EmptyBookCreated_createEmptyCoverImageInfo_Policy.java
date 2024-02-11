package bookGenerator.policy;

import javax.transaction.Transactional;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import bookGenerator._global.config.kafka.KafkaProcessor;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;
import bookGenerator._global.event.EmptyBookCreated;

@Service
@Transactional
public class EmptyBookCreated_createEmptyCoverImageInfo_Policy {

    // 비어있는 책이 만들어졌을 경우, 비어있는 파일 정보를 만들어서 관련 id를 Book에 저장시키기 위해서
    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='EmptyBookCreated'"
    )
    public void emptyBookCreated_createEmptyCoverImageInfo_Policy(
        @Payload EmptyBookCreated emptyBookCreated
    ) {
        try
        {

            CustomLogger.debugObject(CustomLoggerType.ENTER, emptyBookCreated);

            // [1] 새로운 File 객체 생성
            // [!] url은 null로 둔다.

            // [2] EmptyCoverImageInfoCreated 이벤트를 생성된 File 객체로 발생시킨다.

            CustomLogger.debug(CustomLoggerType.EXIT);

        } catch(Exception e) {
            CustomLogger.errorObject(e, "", emptyBookCreated);        
        }
    }

}
