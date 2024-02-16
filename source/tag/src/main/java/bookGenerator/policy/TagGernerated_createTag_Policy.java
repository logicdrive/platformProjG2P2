package bookGenerator.policy;

import javax.transaction.Transactional;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import bookGenerator._global.config.kafka.KafkaProcessor;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;
import bookGenerator._global.event.TagCreated;
import bookGenerator._global.event.TagGernerated;

import bookGenerator.domain.Tag;

@Service
@Transactional
public class TagGernerated_createTag_Policy {

    // AI 기반 태그 생성을 요청해서 태그 생성 이벤트가 발생했을 경우, 해당 이벤트를 기반으로 태그를 새로 생성시키기 위한 정책
    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='TagGernerated'"
    )
    public void tagGernerated_createTag_Policy(
        @Payload TagGernerated tagGernerated
    ) {
        try
        {

            CustomLogger.debugObject(CustomLoggerType.ENTER, tagGernerated);
            

            Tag savedTag = Tag.repository().save(
                Tag.builder()
                    .bookId(tagGernerated.getBookId())
                    .name(tagGernerated.getTagName())
                    .build()
            );
            (new TagCreated(savedTag)).publish();


            CustomLogger.debug(CustomLoggerType.EXIT);

        } catch(Exception e) {
            CustomLogger.errorObject(e, "", tagGernerated);        
        }
    }

}
