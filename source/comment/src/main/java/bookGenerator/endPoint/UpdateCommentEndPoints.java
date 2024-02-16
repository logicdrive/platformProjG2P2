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
          
            // [1] reqDto.commentId로 Comment 객체를 찾음
            Comment comment = CommentManageService.getInstance().findByIdOrThrow(reqDto.getCommentId());

            // [2] reqDto.content로 Comment 객체의 내용을 변경하고 저장함
            comment.setContent(reqDto.getContent());
            Comment.repository().save(comment);

            // [3] CommentUpdated 이벤트를 저장한 Comment 객체로 발생시킴
            (new CommentUpdated(comment)).publish();

            // [4] 저장한 Comment 객체의 ID를 반환
            UpdateCommentResDto updateCommentResDto = new UpdateCommentResDto(comment);
            CustomLogger.debugObject(CustomLoggerType.EXIT, updateCommentResDto);

            return ResponseEntity.ok(updateCommentResDto);
            
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}