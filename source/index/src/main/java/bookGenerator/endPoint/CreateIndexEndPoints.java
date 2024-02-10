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
class CreateIndexReqDto {
    private final Long bookId;
    private final String name;
    private final Long priority;
}

@Getter
@ToString
class CreateIndexResDto {
    private final Long id;

    public CreateIndexResDto(Index index) {
        this.id = index.getId();
    }
}


@RestController
@Transactional
@RequestMapping("/indexes")
public class CreateIndexEndPoints {

    @PutMapping("/createIndex")
    public ResponseEntity<Void> createIndex(@RequestBody CreateIndexReqDto reqDto) {
        try {

            CustomLogger.debugObject(CustomLoggerType.ENTER, reqDto);
            
            // [1] 새로운 Index 객체를 생성
            // [!] bookId, name, priority만 초기화시키면 되며, 다른 변수들은 자동으로 초기화됨

            // [2] IndexCreated 이벤트를 발생시킴

            // [3] 생성된 Index 객체의 id를 반환함

            CustomLogger.debug(CustomLoggerType.EXIT);

            return ResponseEntity.status(HttpStatus.OK).build();

        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
