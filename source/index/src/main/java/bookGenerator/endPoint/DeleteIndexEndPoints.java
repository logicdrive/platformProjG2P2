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
import bookGenerator._global.event.IndexDeleted;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;
import bookGenerator.domain.Index;
import bookGenerator.domain.IndexManageService;


@Data
@ToString
class DeleteIndexReqDto {
    private Long indexId;
}

@Getter
@ToString
class DeleteIndexResDto {
    private final Long id;

    public DeleteIndexResDto(Index index) {
        this.id = index.getId();
    }
}


@RestController
@Transactional
@RequestMapping("/indexes")
public class DeleteIndexEndPoints {

    @PutMapping("/deleteIndex")
    public ResponseEntity<DeleteIndexResDto> deleteIndex(@RequestBody DeleteIndexReqDto reqDto) {
        try {

            CustomLogger.debugObject(CustomLoggerType.ENTER, reqDto);

            // [1] reqDto.indexId로 Index 객체를 찾음
            Index indexToDelete = IndexManageService.getInstance().findByIdOrThrow(reqDto.getIndexId());

            // [2] Index 객체를 삭제
            Index.repository().delete(indexToDelete);

            // [3] IndexDeleted 이벤트를 찾은 Index 객체로 발생시킴
            (new IndexDeleted(indexToDelete)).publish();
            
            // [4] 찾은 Index 객체의 ID를 반환
            DeleteIndexResDto resDto = new DeleteIndexResDto(indexToDelete);
                
            CustomLogger.debug(CustomLoggerType.EXIT);

            return ResponseEntity.ok(resDto);

        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}