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

    // Cover Image가 생성되면 관련 Url을 File 정보에 반영하고 업데이트 이벤트를 발생시키는 정책
    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='CoverImageGenerated'"
    )
    public void coverImageGenerated_updateCoverImageInfo_Policy(
        @Payload CoverImageGenerated coverImageGenerated
    ) {
        try
        {

            CustomLogger.debugObject(CustomLoggerType.ENTER, coverImageGenerated);

            // [1] fileId에 해당하는 File 정보를 조회함

            // [2] 조회된 File 객체의 url을 coverImageGenerated.fileUrl로 변경하고 저장함

            // [3] 조회된 File 객체로 CoverImageInfoUpdated 이벤트를 발생시킴

            CustomLogger.debug(CustomLoggerType.EXIT);

        } catch(Exception e) {
            CustomLogger.errorObject(e, "", coverImageGenerated);        
        }
    }

}
