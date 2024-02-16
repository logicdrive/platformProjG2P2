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

import bookGenerator._global.event.CommentUpdated;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;

import bookGenerator.domain.Comment;
import bookGenerator.domain.CommentManageService;


@Data
@ToString
class UpdateCommentReqDto {
    private Long commentId;
    private String content;
}

@Getter
@ToString
class UpdateCommentResDto {
    private final Long id;

    public UpdateCommentResDto(Comment comment) {
        this.id = comment.getId();
    }
}



@RestController
@Transactional
@RequestMapping("/comments")
public class UpdateCommentEndPoints {

    @PutMapping("/updateComment")
    public ResponseEntity<UpdateCommentResDto> updateComment(@RequestBody UpdateCommentReqDto reqDto) {
        try {

            CustomLogger.debugObject(CustomLoggerType.ENTER, reqDto);
          

            Comment commentToUpdate = CommentManageService.getInstance().findByIdOrThrow(reqDto.getCommentId());
            commentToUpdate.setContent(reqDto.getContent());
            Comment.repository().save(commentToUpdate);

            (new CommentUpdated(commentToUpdate)).publish();


            UpdateCommentResDto resDto = new UpdateCommentResDto(commentToUpdate);
            CustomLogger.debugObject(CustomLoggerType.EXIT, resDto);
            return ResponseEntity.ok(resDto);

        } catch(Exception e) {
            CustomLogger.errorObject(e, "", reqDto);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
