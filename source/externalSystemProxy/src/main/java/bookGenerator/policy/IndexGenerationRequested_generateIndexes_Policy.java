package bookGenerator.policy;

import javax.transaction.Transactional;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import bookGenerator._global.config.kafka.KafkaProcessor;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;
import bookGenerator._global.event.IndexGenerationFailed;
import bookGenerator._global.event.IndexGenerationRequested;
import bookGenerator._global.event.IndexGenereated;
import bookGenerator._global.externalSystemProxy.openai.generateIndexes.ExternalSystemProxy_GenerateIndexes;
import bookGenerator._global.externalSystemProxy.openai.generateIndexes.GenerateIndexesReqDto;
import bookGenerator._global.externalSystemProxy.openai.generateIndexes.GenerateIndexesResDto;

@Service
@Transactional
public class IndexGenerationRequested_generateIndexes_Policy {

    // IndexGenerationRequested 이벤트 발생 관련 정책
    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='IndexGenerationRequested'"
    )
    public void indexGenerationRequested_generateIndexes_Policy(
        @Payload IndexGenerationRequested indexGenerationRequested
    ) {
        try
        {

            CustomLogger.debugObject(CustomLoggerType.ENTER, indexGenerationRequested);


            GenerateIndexesReqDto reqDto = new GenerateIndexesReqDto(indexGenerationRequested.getQuery());
            GenerateIndexesResDto resDto = ExternalSystemProxy_GenerateIndexes.getInstance().externalSystemProxy_GenerateIndexes(reqDto);

            for(int i = 0; i < resDto.getIndexNames().size(); i++) {
                String indexName = resDto.getIndexNames().get(i);
                (new IndexGenereated(indexGenerationRequested.getBookId(), indexName, Long.valueOf(i+1))).publish();
            }


            CustomLogger.debug(CustomLoggerType.EXIT);

        } catch(Exception e) {
            CustomLogger.errorObject(e, "", indexGenerationRequested);
            
            (new IndexGenerationFailed(indexGenerationRequested.getBookId())).publish();
        }
    }

}
