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
class CreateTagReqDto {
    private final Long bookId;
    private final String name;
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
    public ResponseEntity<Void> createTag(CreateTagReqDto reqDto) {
        try {

            CustomLogger.debug(CustomLoggerType.ENTER, reqDto.toString());

            // [1] 새로운 Tag 객체를 생성
            // [!] bookId, name만 초기화시키면 되며, 다른 변수들은 자동으로 초기화됨

            // [2] TagCreated 이벤트를 발생시킴

            // [3] 생성된 Tag 객체의 id를 반환함
                
            CustomLogger.debug(CustomLoggerType.EXIT);

            return ResponseEntity.status(HttpStatus.OK).build();

        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
