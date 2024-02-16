package bookGenerator.policy;

import javax.transaction.Transactional;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import bookGenerator._global.config.kafka.KafkaProcessor;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;
import bookGenerator._global.event.ContentGenerated;
import bookGenerator._global.event.ContentGenerationFailed;
import bookGenerator._global.event.ContentGenerationRequested;
import bookGenerator._global.externalSystemProxy.openai.generateContent.ExternalSystemProxy_GenerateContent;
import bookGenerator._global.externalSystemProxy.openai.generateContent.GenerateContentReqDto;
import bookGenerator._global.externalSystemProxy.openai.generateContent.GenerateContentResDto;

@Service
@Transactional
public class ContentGenerationRequested_generateContent_Policy {

    // ContentGenerationRequested 이벤트 발생 관련 정책
    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='ContentGenerationRequested'"
    )
    public void contentGenerationRequested_generateContent_Policy(
        @Payload ContentGenerationRequested contentGenerationRequested
    ) {
        try
        {

            CustomLogger.debugObject(CustomLoggerType.ENTER, contentGenerationRequested);


            GenerateContentReqDto reqDto = new GenerateContentReqDto(contentGenerationRequested.getQuery());
            GenerateContentResDto resDto = ExternalSystemProxy_GenerateContent.getInstance().externalSystemProxy_GenerateContent(reqDto);

            (new ContentGenerated(contentGenerationRequested.getId(), resDto.getContent())).publish();


            CustomLogger.debug(CustomLoggerType.EXIT);

        } catch(Exception e) {
            CustomLogger.errorObject(e, "", contentGenerationRequested);      
            
            (new ContentGenerationFailed(contentGenerationRequested.getId())).publish();
        }
    }

}
