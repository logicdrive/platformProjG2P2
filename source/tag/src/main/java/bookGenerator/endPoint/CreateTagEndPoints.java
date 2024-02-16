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

import bookGenerator._global.event.TagCreated;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;
import bookGenerator.domain.Tag;


@Data
@ToString
class CreateTagReqDto {
    private Long bookId;
    private String name;
}

@Getter
@ToString
class CreateTagResDto {
    private final Long id;

    public CreateTagResDto(Tag tag) {
        this.id = tag.getId();
    }
}


@RestController
@Transactional
@RequestMapping("/tags")
public class CreateTagEndPoints {

    @PutMapping("/createTag")
    public ResponseEntity<CreateTagResDto> createTag(@RequestBody CreateTagReqDto reqDto) {
        try {

            CustomLogger.debugObject(CustomLoggerType.ENTER, reqDto);


            Tag savedTag = Tag.repository().save(
                Tag.builder()
                    .bookId(reqDto.getBookId())
                    .name(reqDto.getName())
                    .build()
            );
            (new TagCreated(savedTag)).publish();


            CreateTagResDto resDto = new CreateTagResDto(savedTag);
            CustomLogger.debugObject(CustomLoggerType.EXIT, resDto);
            return ResponseEntity.ok(resDto);

        } catch(Exception e) {
            CustomLogger.errorObject(e, "", reqDto); 
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
