package bookGenerator.policy;

import javax.transaction.Transactional;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import bookGenerator._global.config.kafka.KafkaProcessor;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;
import bookGenerator._global.event.CoverImageGenerated;
import bookGenerator._global.event.CoverImageGenerationFailed;
import bookGenerator._global.event.CoverImageGenerationRequestedByFile;
import bookGenerator._global.externalSystemProxy.google.generateSearchImage.ExternalSystemProxy_GenerateSearchImage;
import bookGenerator._global.externalSystemProxy.google.generateSearchImage.GenerateSearchImageReqDto;
import bookGenerator._global.externalSystemProxy.google.generateSearchImage.GenerateSearchImageResDto;

@Service
@Transactional
public class CoverImageGenerationRequestedByFile_generateCoverImage_Policy {

    // CoverImageGenerationRequestedByFile 이벤트 발생 관련 정책
    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='CoverImageGenerationRequestedByFile'"
    )
    public void coverImageGenerationRequestedByFile_generateCoverImage_Policy(
        @Payload CoverImageGenerationRequestedByFile coverImageGenerationRequestedByFile
    ) {
        try
        {

            CustomLogger.debugObject(CustomLoggerType.ENTER, coverImageGenerationRequestedByFile);


            GenerateSearchImageReqDto reqDto = new GenerateSearchImageReqDto(coverImageGenerationRequestedByFile.getQuery());
            GenerateSearchImageResDto resDto = ExternalSystemProxy_GenerateSearchImage.getInstance().externalSystemProxy_GenerateSearchImage(reqDto);

            (new CoverImageGenerated(coverImageGenerationRequestedByFile.getId(), resDto.getFileUrl())).publish();


            CustomLogger.debug(CustomLoggerType.EXIT);

        } catch(Exception e) {
            CustomLogger.errorObject(e, "", coverImageGenerationRequestedByFile);      
            
            (new CoverImageGenerationFailed(coverImageGenerationRequestedByFile.getId())).publish();
        }
    }

}
