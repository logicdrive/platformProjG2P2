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

import bookGenerator._global.event.BookShelfIsSharedUpdated;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;

import bookGenerator.domain.BookShelf;
import bookGenerator.domain.BookShelfManageService;


@Data
@ToString
class UpdateIsSharedReqDto {
    private Long bookShelfId;
    private Boolean isShared;
}

@Getter
@ToString
class UpdateIsSharedResDto {
    private final Long id;

    public UpdateIsSharedResDto(BookShelf bookShelf) {
        this.id = bookShelf.getId();
    }
}


@RestController
@Transactional
@RequestMapping("/bookShelfs")
public class UpdateIsSharedEndPoints {

    @PutMapping("/updateIsShared")
    public ResponseEntity<UpdateIsSharedResDto> updateIsShared(@RequestBody UpdateIsSharedReqDto reqDto) {
        try {

            CustomLogger.debugObject(CustomLoggerType.ENTER, reqDto);


            BookShelf bookShelfToUpdate = BookShelfManageService.getInstance().findByIdOrThrow(reqDto.getBookShelfId());
            bookShelfToUpdate.setIsShared(reqDto.getIsShared());
            BookShelf savedBookShelf = BookShelf.repository().save(bookShelfToUpdate);

            (new BookShelfIsSharedUpdated(savedBookShelf)).publish();


            UpdateIsSharedResDto resDto = new UpdateIsSharedResDto(savedBookShelf);
            CustomLogger.debugObject(CustomLoggerType.EXIT, resDto);
            return ResponseEntity.ok(resDto);

        } catch(Exception e) {
            CustomLogger.errorObject(e, "", reqDto);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
