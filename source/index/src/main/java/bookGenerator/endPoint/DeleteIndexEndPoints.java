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


            Index indexToDelete = IndexManageService.getInstance().findByIdOrThrow(reqDto.getIndexId());
            Index.repository().delete(indexToDelete);

            (new IndexDeleted(indexToDelete)).publish();
            

            DeleteIndexResDto resDto = new DeleteIndexResDto(indexToDelete);
            CustomLogger.debugObject(CustomLoggerType.EXIT, resDto);
            return ResponseEntity.ok(resDto);

        } catch(Exception e) {
            CustomLogger.errorObject(e, "", reqDto); 
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
