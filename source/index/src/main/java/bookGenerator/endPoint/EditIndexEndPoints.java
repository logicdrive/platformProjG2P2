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

import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;
import bookGenerator.domain.Index;


@Data
@ToString
class EditIndexReqDto {
    private final Long indexId;
    private final String indexName;
    private final Long indexPriority;
}

@Getter
@ToString
class EditIndexResDto {
    private final Long id;

    public EditIndexResDto(Index index) {
        this.id = index.getId();
    }
}


@RestController
@Transactional
@RequestMapping("/indexes")
public class EditIndexEndPoints {

    @PutMapping("/editIndex")
    public ResponseEntity<Void> editIndex(@RequestBody EditIndexReqDto reqDto) {
        try {

            CustomLogger.debugObject(CustomLoggerType.ENTER, reqDto);

            // [1] indexId에 해당하는 Index 객체를 찾음

            // [2] Index 객체의 name, priority을 변경함

            // [3] 저장된 Index를 기반으로 IndexEdited 이벤트를 발생시킴

            // [4] 저장된 Index Id를 반환함
                
            CustomLogger.debug(CustomLoggerType.EXIT);

            return ResponseEntity.status(HttpStatus.OK).build();

        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
