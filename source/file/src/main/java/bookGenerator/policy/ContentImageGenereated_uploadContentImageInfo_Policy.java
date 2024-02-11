package bookGenerator.policy;

import javax.transaction.Transactional;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import bookGenerator._global.config.kafka.KafkaProcessor;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;
import bookGenerator._global.event.ContentImageGenereated;

@Service
@Transactional
public class ContentImageGenereated_uploadContentImageInfo_Policy {

    // Content 이미지가 생성되었을 경우, 관련 Url을 기반으로 새로운 File을 만들고, 그것을 해당 content에 알리는 정책
    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='ContentImageGenereated'"
    )
    public void contentImageGenereated_uploadContentImageInfo_Policy(
        @Payload ContentImageGenereated contentImageGenereated
    ) {
        try
        {
            
            CustomLogger.debugObject(CustomLoggerType.ENTER, contentImageGenereated);

            // [1] contentImageGenereated.fileUrl로 새로운 File 객체 생성
            // [!] url만 초기화시키면 되며, 다른 변수들은 자동으로 초기화됨

            // [2] ContentImageInfoUploaded 이벤트를 생성된 File 객체와 contentImageGenereated.contentId로 발생시킨다
            
            CustomLogger.debug(CustomLoggerType.EXIT);

        } catch(Exception e) {
            CustomLogger.errorObject(e, "", contentImageGenereated);        
        }
    }

}
