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

import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;
import bookGenerator.domain.Comment;


@Data
@ToString
class DeleteCommentReqDto {
    private final Long commentId;
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
    public ResponseEntity<Void> deleteComment(@RequestBody DeleteCommentResDto reqDto) {
        try {

            CustomLogger.debugObject(CustomLoggerType.ENTER, reqDto);

            // [1] reqDto.commentId로 Comment 객체를 찾음

            // [2] Comment 객체를 삭제

            // [3] CommentDeleted 이벤트를 찾은 Comment 객체로 발생시킴
            
            // [4] 찾은 Comment 객체의 ID를 반환
                
            CustomLogger.debug(CustomLoggerType.EXIT);

            return ResponseEntity.status(HttpStatus.OK).build();

        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
