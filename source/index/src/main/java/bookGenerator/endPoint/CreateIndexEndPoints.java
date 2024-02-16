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
import bookGenerator._global.event.IndexCreated;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;
import bookGenerator.domain.Index;


@Data
@ToString
class CreateIndexReqDto {
    private Long bookId;
    private String name;
    private Long priority;
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
    public ResponseEntity<CreateIndexResDto> createIndex(@RequestBody CreateIndexReqDto reqDto) {
        try {

            CustomLogger.debugObject(CustomLoggerType.ENTER, reqDto);
            
            
            Index savedIndex = Index.repository().save(
                Index.builder()
                    .bookId(reqDto.getBookId())
                    .name(reqDto.getName())
                    .priority(reqDto.getPriority())
                    .build()
            );
            (new IndexCreated(savedIndex)).publish();
            

            CreateIndexResDto resDto = new CreateIndexResDto(savedIndex);
            CustomLogger.debugObject(CustomLoggerType.EXIT, resDto);
            return ResponseEntity.ok(resDto);

        } catch(Exception e) {
            CustomLogger.errorObject(e, "", reqDto); 
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
