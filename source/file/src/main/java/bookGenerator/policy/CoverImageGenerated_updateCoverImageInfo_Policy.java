package bookGenerator.policy;

import javax.transaction.Transactional;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import bookGenerator._global.config.kafka.KafkaProcessor;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;
import bookGenerator._global.event.CoverImageGenerated;

@Service
@Transactional
public class CoverImageGenerated_updateCoverImageInfo_Policy {

    // CoverImageGenerated 이벤트 발생 관련 정책
    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='CoverImageGenerated'"
    )
    public void coverImageGenerated_updateCoverImageInfo_Policy(
        @Payload CoverImageGenerated coverImageGenerated
    ) {
        try
        {
            
            CustomLogger.debugObject(CustomLoggerType.ENTER_EXIT, "CoverImageGenerated", coverImageGenerated);

        } catch(Exception e) {
            CustomLogger.errorObject(e, "", coverImageGenerated);        
        }
    }

}
