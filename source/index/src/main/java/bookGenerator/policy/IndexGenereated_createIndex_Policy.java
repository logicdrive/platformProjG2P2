package bookGenerator.policy;

import javax.transaction.Transactional;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import bookGenerator._global.config.kafka.KafkaProcessor;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;
import bookGenerator._global.event.IndexCreated;
import bookGenerator._global.event.IndexGenereated;
import bookGenerator.domain.Index;

@Service
@Transactional
public class IndexGenereated_createIndex_Policy {

    // AI 기반 인덱스 생성을 요청해서 인덱스 생성 이벤트가 발생했을 경우, 해당 이벤트를 기반으로 인덱스를 새로 생성시키기 위한 정책
    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='IndexGenereated'"
    )
    public void indexGenereated_createIndex_Policy(
        @Payload IndexGenereated indexGenereated
    ) {
        try
        {
            
            CustomLogger.debugObject(CustomLoggerType.ENTER, indexGenereated);
            

            Index savedIndex = Index.repository().save(
                Index.builder()
                    .bookId(indexGenereated.getBookId())
                    .name(indexGenereated.getIndexName())
                    .priority(indexGenereated.getPriority())
                    .build()
            );
            (new IndexCreated(savedIndex)).publish();


            CustomLogger.debug(CustomLoggerType.EXIT);

        } catch(Exception e) {
            CustomLogger.errorObject(e, "", indexGenereated);        
        }
    }

}
