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

import bookGenerator._global.event.IndexEdited;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;

import bookGenerator.domain.Index;
import bookGenerator.domain.IndexManageService;


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

    @PutMapping("/editIndex")
    public ResponseEntity<EditIndexResDto> editIndex(@RequestBody EditIndexReqDto reqDto) {
        try {

            CustomLogger.debugObject(CustomLoggerType.ENTER, reqDto);


            Index indexToUpdate = IndexManageService.getInstance().findByIdOrThrow(reqDto.getIndexId());
            indexToUpdate.setName(reqDto.getIndexName());
            indexToUpdate.setPriority(reqDto.getIndexPriority());
            Index savedIndex = Index.repository().save(indexToUpdate);

            (new IndexEdited(savedIndex)).publish();


            EditIndexResDto resDto = new EditIndexResDto(savedIndex);
            CustomLogger.debugObject(CustomLoggerType.EXIT, resDto);
            return ResponseEntity.ok(resDto);

        } catch(Exception e) {
            CustomLogger.errorObject(e, "", reqDto); 
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
