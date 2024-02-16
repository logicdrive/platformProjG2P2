package bookGenerator.policy;

import javax.transaction.Transactional;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import bookGenerator._global.config.kafka.KafkaProcessor;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;
import bookGenerator._global.event.TagGenerationFailed;
import bookGenerator._global.event.TagGenerationRequested;
import bookGenerator._global.event.TagGernerated;
import bookGenerator._global.externalSystemProxy.openai.generateTags.ExternalSystemProxy_GenerateTags;
import bookGenerator._global.externalSystemProxy.openai.generateTags.GenerateTagsReqDto;
import bookGenerator._global.externalSystemProxy.openai.generateTags.GenerateTagsResDto;

@Service
@Transactional
public class TagGenerationRequested_genereateTags_Policy {

    // TagGenerationRequested 이벤트 발생 관련 정책
    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='TagGenerationRequested'"
    )
    public void tagGenerationRequested_genereateTags_Policy(
        @Payload TagGenerationRequested tagGenerationRequested
    ) {
        try
        {
            
            CustomLogger.debugObject(CustomLoggerType.ENTER, tagGenerationRequested);

            
            GenerateTagsReqDto reqDto = new GenerateTagsReqDto(tagGenerationRequested.getQuery());
            GenerateTagsResDto resDto = ExternalSystemProxy_GenerateTags.getInstance().externalSystemProxy_GenerateTags(reqDto);

            resDto.getTagNames().forEach(
                tagName -> {
                    (new TagGernerated(tagGenerationRequested.getBookId(), tagName)).publish();
                }
            );
            

            CustomLogger.debug(CustomLoggerType.EXIT);

        } catch(Exception e) {
            CustomLogger.errorObject(e, "", tagGenerationRequested);
            
            (new TagGenerationFailed(tagGenerationRequested.getBookId())).publish();
        }
    }

}
