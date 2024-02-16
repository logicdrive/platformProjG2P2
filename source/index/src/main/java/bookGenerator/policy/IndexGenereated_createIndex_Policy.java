package bookGenerator.policy;

import javax.transaction.Transactional;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import bookGenerator._global.config.kafka.KafkaProcessor;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;
import bookGenerator.domain.Index;
import bookGenerator._global.event.IndexCreated;
import bookGenerator._global.event.IndexGenereated;

@Service
@Transactional
public class IndexGenereated_createIndex_Policy {

    // AI 기반 인덱스 생성을 요청해서 인덱스 생성 이벤트가 발생했을 경우, 해당 이벤트를 기반으로 인덱스를 새로 생성시키기 위한 정책
    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='IndexGenereated'"
    )
    public ResponseEntity<Object> indexGenereated_createIndex_Policy(
        @Payload IndexGenereated indexGenereated
    ) {
        try
        {
            
            CustomLogger.debugObject(CustomLoggerType.ENTER, indexGenereated);
            
            // [1] 새로운 Index 객체를 생성
            // [!] bookId, name, priority만 초기화시키면 되며, 다른 변수들은 자동으로 초기화됨
            Index savedIndex = Index.repository()
                    .save(Index.builder()
                    .bookId(indexGenereated.getBookId())
                    .name(indexGenereated.getIndexName())
                    .priority(indexGenereated.getPriority()).build());

            // [2] IndexCreated 이벤트를 발생시킴
            (new IndexCreated(savedIndex)).publish();

            CustomLogger.debug(CustomLoggerType.EXIT);
        } catch(Exception e) {
            CustomLogger.errorObject(e, "", indexGenereated);        
        }
    }

}
