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
import bookGenerator._global.event.TagDeleted;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;
import bookGenerator.domain.Tag;
import bookGenerator.domain.TagManageService;


@Data
@ToString
class DeleteTagReqDto {
    private Long tagId;
}

@Getter
@ToString
class DeleteTagResDto {
    private final Long id;

    public DeleteTagResDto(Tag tag) {
        this.id = tag.getId();
    }
}


@RestController
@Transactional
@RequestMapping("/tags")
public class DeleteTagEndPoints {

    @PutMapping("/deleteTag")
    public ResponseEntity<DeleteTagResDto> deleteTag(@RequestBody DeleteTagReqDto reqDto) {
        try {

            CustomLogger.debugObject(CustomLoggerType.ENTER, reqDto);

            // [1] reqDto.tagId로 Tag 객체를 찾음
            Tag tagToDelete = TagManageService.getInstance().findByIdOrThrow(reqDto.getTagId());

            // [2] Tag 객체를 삭제
            Tag.repository().delete(tagToDelete);

            // [3] TagDeleted 이벤트를 찾은 Tag 객체로 발생시킴
            (new TagDeleted(tagToDelete)).publish();
            
            // [4] 찾은 Tag 객체의 ID를 반환
            DeleteTagResDto resDto = new DeleteTagResDto(tagToDelete);

            CustomLogger.debugObject(CustomLoggerType.EXIT, resDto);

            return ResponseEntity.ok(resDto);
            // Fin

        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
