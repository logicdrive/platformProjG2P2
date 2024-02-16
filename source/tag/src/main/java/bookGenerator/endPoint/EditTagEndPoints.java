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
import bookGenerator._global.event.TagEdited;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;
import bookGenerator.domain.Tag;
import bookGenerator.domain.TagManageService;


@Data
@ToString
class EditTagReqDto {
    private Long tagId;
    private String tagName;
}


@Getter
@ToString
class EditTagResDto {
    private final Long id;

    public EditTagResDto(Tag tag) {
        this.id = tag.getId();
    }
}


@RestController
@Transactional
@RequestMapping("/tags")
public class EditTagEndPoints {

    @PutMapping("/editTag")
    public ResponseEntity<Void> editTag(@RequestBody EditTagReqDto reqDto) {
        try {

            CustomLogger.debugObject(CustomLoggerType.ENTER, reqDto);

            // [1] tagId에 해당하는 Tag 객체를 찾음
            Tag tagToUpdate = TagManageService.getInstance().findByIdOrThrow(reqDto.getTagId());

            // [2] Tag 객체의 name을 변경함
            tagToUpdate.setName(reqDto.getTagName());

            // [3] 저장된 Tag를 기반으로 TagEdited 이벤트를 발생시킴
            Tag savedTag = Tag.repository().save(tagToUpdate);

            (new TagEdited(savedTag)).publish();

            // [4] 저장된 Tag Id를 반환함
            EditTagResDto resDto = new EditTagResDto(savedTag);

            CustomLogger.debug(CustomLoggerType.EXIT);

            return ResponseEntity.status(HttpStatus.OK).build();

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
