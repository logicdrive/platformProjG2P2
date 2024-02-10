package bookGenerator.endPoint;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;

import javax.transaction.Transactional;

import lombok.Data;
import lombok.Getter;
import lombok.ToString;

import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;
import bookGenerator.domain.Tag;


@Data
@ToString
class DeleteTagReqDto {
    private final Long tagId;
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
    public ResponseEntity<Void> deleteTag(DeleteTagReqDto reqDto) {
        try {

            CustomLogger.debugObject(CustomLoggerType.ENTER, reqDto);
                
            // [1] reqDto.tagId로 Tag 객체를 찾음

            // [2] Tag 객체를 삭제

            // [3] TagDeleted 이벤트를 찾은 Tag 객체로 발생시킴
            
            // [4] 찾은 Tag 객체의 ID를 반환

            CustomLogger.debug(CustomLoggerType.EXIT);

            return ResponseEntity.status(HttpStatus.OK).build();

        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
