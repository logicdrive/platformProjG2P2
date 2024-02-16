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

import bookGenerator._global.event.CommentDeleted;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;

import bookGenerator.domain.Comment;
import bookGenerator.domain.CommentManageService;


@Data
@ToString
class DeleteCommentReqDto {
    private Long commentId;
}

@Getter
@ToString
class DeleteCommentResDto {
    private final Long id;

    public DeleteCommentResDto(Comment comment) {
        this.id = comment.getId();
    }
}


@RestController
@Transactional
@RequestMapping("/comments")
public class DeleteCommentEndPoints {

    @PutMapping("/deleteComment")
    public ResponseEntity<DeleteCommentResDto> deleteComment(@RequestBody DeleteCommentReqDto reqDto) {
        try {

            CustomLogger.debugObject(CustomLoggerType.ENTER, reqDto);


            Comment commentToDelete = CommentManageService.getInstance().findByIdOrThrow(reqDto.getCommentId());
            Comment.repository().delete(commentToDelete);

            (new CommentDeleted(commentToDelete)).publish();
            

            DeleteCommentResDto resDto = new DeleteCommentResDto(commentToDelete);
            CustomLogger.debugObject(CustomLoggerType.EXIT, resDto);
            return ResponseEntity.ok(resDto);

        } catch(Exception e) {
            CustomLogger.errorObject(e, "", reqDto);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
