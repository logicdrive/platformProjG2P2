package bookGenerator.policy;

import javax.transaction.Transactional;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import bookGenerator._global.config.kafka.KafkaProcessor;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;
import bookGenerator._global.event.ContentImageGenerationFailed;
import bookGenerator._global.event.ContentImageGenerationRequested;
import bookGenerator._global.event.ContentImageGenereated;
import bookGenerator._global.externalSystemProxy.google.generateSearchImage.ExternalSystemProxy_GenerateSearchImage;
import bookGenerator._global.externalSystemProxy.google.generateSearchImage.GenerateSearchImageReqDto;
import bookGenerator._global.externalSystemProxy.google.generateSearchImage.GenerateSearchImageResDto;

@Service
@Transactional
public class ContentImageGenerationRequested_generateContentImage_Policy {

    // ContentImageGenerationRequested 이벤트 발생 관련 정책
    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='ContentImageGenerationRequested'"
    )
    public void contentImageGenerationRequested_generateContentImage_Policy(
        @Payload ContentImageGenerationRequested contentImageGenerationRequested
    ) {
        try
        {

            CustomLogger.debugObject(CustomLoggerType.ENTER, contentImageGenerationRequested);


            GenerateSearchImageReqDto reqDto = new GenerateSearchImageReqDto(contentImageGenerationRequested.getQuery());
            GenerateSearchImageResDto resDto = ExternalSystemProxy_GenerateSearchImage.getInstance().externalSystemProxy_GenerateSearchImage(reqDto);

            (new ContentImageGenereated(contentImageGenerationRequested.getId(), resDto.getFileUrl())).publish();


            CustomLogger.debug(CustomLoggerType.EXIT);

        } catch(Exception e) {
            CustomLogger.errorObject(e, "", contentImageGenerationRequested);
            
            (new ContentImageGenerationFailed(contentImageGenerationRequested.getId())).publish();
        }
    }

}
