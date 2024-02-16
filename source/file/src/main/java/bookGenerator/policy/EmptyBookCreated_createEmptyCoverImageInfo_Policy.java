package bookGenerator.policy;

import javax.transaction.Transactional;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import bookGenerator._global.config.kafka.KafkaProcessor;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;
import bookGenerator._global.event.EmptyBookCreated;
import bookGenerator._global.event.EmptyCoverImageInfoCreated;

import bookGenerator.domain.File;

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

            
            File savedFile = File.repository().save(
                File.builder()
                    .url(null)
                    .build()
            );
            (new EmptyCoverImageInfoCreated(savedFile, emptyBookCreated.getId())).publish();


            CustomLogger.debug(CustomLoggerType.EXIT);

        } catch(Exception e) {
            CustomLogger.errorObject(e, "", emptyBookCreated);        
        }
    }

}
