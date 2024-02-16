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
    public ResponseEntity<EditTagResDto> editTag(@RequestBody EditTagReqDto reqDto) {
        try {

            CustomLogger.debugObject(CustomLoggerType.ENTER, reqDto);


            Tag tagToUpdate = TagManageService.getInstance().findByIdOrThrow(reqDto.getTagId());
            tagToUpdate.setName(reqDto.getTagName());
            Tag savedTag = Tag.repository().save(tagToUpdate);

            (new TagEdited(savedTag)).publish();


            EditTagResDto resDto = new EditTagResDto(savedTag);
            CustomLogger.debugObject(CustomLoggerType.EXIT, resDto);
            return ResponseEntity.ok(resDto);

        } catch(Exception e) {
            CustomLogger.errorObject(e, "", reqDto); 
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
