package bookGenerator.endPoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;

import javax.transaction.Transactional;

import lombok.Data;
import lombok.Getter;
import lombok.ToString;
import bookGenerator._global.event.ContentGenerationRequested;
import bookGenerator._global.event.ContentImageGenerationRequested;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;
import bookGenerator.domain.Content;


@Data
@ToString
class GenerateContentReqDto {
    private Long indexId;
}

@Getter
@ToString
class GenerateContentResDto {
    private final Long id;

    public GenerateContentResDto(Content content) {
        this.id = content.getId();
    }
}


@RestController
@Transactional
@RequestMapping("/contents")
public class GenerateContentEndPoints {

    @PutMapping("/generateContent")
    public ResponseEntity<GenerateContentResDto> generateContent(@RequestBody GenerateContentReqDto reqDto) {
        try {

            CustomLogger.debug(CustomLoggerType.ENTER, reqDto.toString());

            // [1] 새로운 Content 객체를 생성
            // [!] indexId만 초기화시키면 되며, 다른 변수들은 자동으로 초기화됨
            Content content = Content.repository().save(
                Content.builder()
                .indexId(reqDto.getIndexId())
                .build()
            );

            // [2] ContentImageGenerationRequested 이벤트를 생성된 Content 기반으로 발생시킴
            Content contentImageGeneration = Content.repository().save(content);
            (new ContentImageGenerationRequested(contentImageGeneration)).publish();

            // [3] ContentGenerationRequested 이벤트를 생성된 Content 기반으로 발생시킴
            Content contentGeneration = Content.repository().save(content);
            (new ContentGenerationRequested(contentGeneration)).publish();

            // [4] 생성된 Content 객체의 id를 반환
            GenerateContentResDto responseDto = new GenerateContentResDto(content);
                
            CustomLogger.debug(CustomLoggerType.EXIT);

            return ResponseEntity.ok().body(responseDto);

        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}