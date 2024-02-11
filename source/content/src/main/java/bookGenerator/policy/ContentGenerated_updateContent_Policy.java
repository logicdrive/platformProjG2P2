package bookGenerator.policy;

import javax.transaction.Transactional;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import bookGenerator._global.config.kafka.KafkaProcessor;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;
import bookGenerator._global.event.ContentGenerated;

@Service
@Transactional
public class ContentGenerated_updateContent_Policy {

    // 컨텐츠 생성을 요청한 파일에 대한 정보가 이벤트로 발생되었을 때, 관련 content를 업데이트하기 위해서
    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='ContentGenerated'"
    )
    public void contentGenerated_updateContent_Policy(
        @Payload ContentGenerated contentGenerated
    ) {
        try
        {
            
            CustomLogger.debugObject(CustomLoggerType.ENTER, contentGenerated);

            // [1] contentGenerated.contentId를 이용하여 Content를 조회함

            // [2] 조회된 Content의 content를 contentGenerated.content로 업데이트함

            // [3] ContentUpdated 이벤트를 업데이트된 Content를 기반으로 발생시킴

            CustomLogger.debug(CustomLoggerType.EXIT);

        } catch(Exception e) {
            CustomLogger.errorObject(e, "", contentGenerated);        
        }
    }

}
