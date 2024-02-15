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

            // [1] 새로운 Tag 객체를 생성
            // [!] bookId, name만 초기화시키면 되며, 다른 변수들은 자동으로 초기화됨
            Tag tagToCreate = Tag.repository().save(
                Tag.builder()
                .bookId(reqDto.getBookId())
                .name(reqDto.getName())
                .build()
            );

            // [2] TagCreated 이벤트를 발생시킴
            (new TagCreated(tagToCreate)).publish();

            // [3] 생성된 Tag 객체의 id를 반환함
            CreateTagResDto resDto = new CreateTagResDto(tagToCreate);
                
            CustomLogger.debug(CustomLoggerType.EXIT);

            return ResponseEntity.ok(resDto);

        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}