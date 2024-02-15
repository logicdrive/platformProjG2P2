package bookGenerator.endPoint;

import org.springframework.beans.factory.annotation.Autowired;
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
import bookGenerator._global.event.IndexEdited;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;
import bookGenerator.domain.Index;
import bookGenerator.domain.IndexRepository;


@Data
@ToString
class EditIndexReqDto {
    private Long indexId;
    private String indexName;
    private Long indexPriority;
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

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    private IndexRepository indexRepository;

    @PutMapping("/editIndex")
    public ResponseEntity<EditIndexResDto> editIndex(@RequestBody EditIndexReqDto reqDto) {
        try {

            CustomLogger.debugObject(CustomLoggerType.ENTER, reqDto);

            // [1] indexId에 해당하는 Index 객체를 찾음
            Index index = indexRepository.findById(reqDto.getIndexId()).orElse(null);
            if (index == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

            // [2] Index 객체의 name, priority을 변경함
            index.setName(reqDto.getIndexName());
            index.setPriority(reqDto.getIndexPriority());

            // [3] 저장된 Index를 기반으로 IndexEdited 이벤트를 발생시킴
            IndexEdited event = new IndexEdited(index);
            applicationEventPublisher.publishEvent(event);

            // [4] 저장된 Index Id를 반환함
            EditIndexResDto responseDto = new EditIndexResDto(index);

            CustomLogger.debug(CustomLoggerType.EXIT);

            return ResponseEntity.ok().body(responseDto);

        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}