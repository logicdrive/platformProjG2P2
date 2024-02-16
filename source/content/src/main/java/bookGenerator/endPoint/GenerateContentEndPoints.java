package bookGenerator.endPoint;

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
    private String query;
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

            CustomLogger.debugObject(CustomLoggerType.ENTER, reqDto);


            Content savedContent = Content.repository().save(
                Content.builder()
                    .indexId(reqDto.getIndexId())
                    .build()
            );
            (new ContentImageGenerationRequested(savedContent, reqDto.getQuery())).publish();
            (new ContentGenerationRequested(savedContent, reqDto.getQuery())).publish();


            GenerateContentResDto resDto = new GenerateContentResDto(savedContent);
            CustomLogger.debugObject(CustomLoggerType.EXIT, resDto);
            return ResponseEntity.ok(resDto);

        } catch(Exception e) {
            CustomLogger.errorObject(e, "", reqDto); 
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
