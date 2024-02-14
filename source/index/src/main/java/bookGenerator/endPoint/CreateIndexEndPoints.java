package bookGenerator.endPoint;

import org.springframework.context.ApplicationEventPublisher;
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
    private final ApplicationEventPublisher eventPublisher;

    public CreateIndexEndPoints(ApplicationEventPublisher eventPublisher) {
        if (eventPublisher == null) {
            throw new IllegalArgumentException("eventPublisher cannot be null");
        }
        this.eventPublisher = eventPublisher;
    }

    @PutMapping("/createIndex")
    public ResponseEntity<CreateIndexResDto> createIndex(@RequestBody CreateIndexReqDto reqDto) {
        try {
            if (reqDto == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }

            CustomLogger.debugObject(CustomLoggerType.ENTER, reqDto);

            // [1] 새로운 Index 객체를 생성
            // [!] bookId, name, priority만 초기화시키면 되며, 다른 변수들은 자동으로 초기화됨
            Index index = Index.builder()
                    .bookId(reqDto.getBookId())
                    .name(reqDto.getName())
                    .priority(reqDto.getPriority())
                    .build();
            index = Index.repository().save(index);
            if (index == null) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            }

            // [2] IndexCreated 이벤트를 발생시킴
            IndexCreated indexCreatedEvent = new IndexCreated(index);
            eventPublisher.publishEvent(indexCreatedEvent);

            // [3] 생성된 Index 객체의 id를 반환함

            CreateIndexResDto resDto = new CreateIndexResDto(index);

            CustomLogger.debug(CustomLoggerType.EXIT);

            return ResponseEntity.ok(resDto);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
