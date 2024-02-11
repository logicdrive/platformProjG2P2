package bookGenerator.policy;

import javax.transaction.Transactional;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import bookGenerator._global.config.kafka.KafkaProcessor;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;
import bookGenerator._global.event.ContentImageInfoUploaded;

@Service
@Transactional
public class ContentImageInfoUploaded_updateImageFileId_Policy {

    // 이미지 생성을 요청한 파일에 대한 정보가 이벤트로 발생되었을 때, 관련 imageFileId를 업데이트하기 위해서
    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='ContentImageInfoUploaded'"
    )
    public void contentImageInfoUploaded_updateImageFileId_Policy(
        @Payload ContentImageInfoUploaded contentImageInfoUploaded
    ) {
        try
        {
            CustomLogger.debugObject(CustomLoggerType.ENTER, contentImageInfoUploaded);

            // [1] contentImageInfoUploaded.contentId를 이용하여 Content를 조회함

            // [2] 조회된 Content의 imageFileId를 contentImageInfoUploaded.id로 업데이트함

            // [3] ContentImageFileIdUpdated 이벤트를 업데이트된 Content를 기반으로 발생시킴

            CustomLogger.debug(CustomLoggerType.EXIT);

        } catch(Exception e) {
            CustomLogger.errorObject(e, "", contentImageInfoUploaded);        
        }
    }

}
