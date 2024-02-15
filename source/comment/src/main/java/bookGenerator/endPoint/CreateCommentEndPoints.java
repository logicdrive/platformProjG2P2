package bookGenerator.endPoint;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;

import javax.transaction.Transactional;

import lombok.Data;
import lombok.Getter;
import lombok.ToString;
import bookGenerator._global.event.CommentCreated;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;
import bookGenerator.domain.Comment;


@Data
@ToString
class CreateCommentReqDto {
    private Long bookId;
    private String content;
}

@Getter
@ToString
class CreateCommentResDto {
    private final Long id;

    public CreateCommentResDto(Comment comment) {
        this.id = comment.getId();
    }
}


@RestController
@Transactional
@RequestMapping("/comments")
public class CreateCommentEndPoints {
    @PutMapping("/createComment")
    public ResponseEntity<CreateCommentResDto> createComment(@RequestHeader("User-Id") Long userId, @RequestBody CreateCommentReqDto reqDto) {
        try {

            CustomLogger.debugObject(CustomLoggerType.ENTER, reqDto);

            // [1] 새로운 Comment 객체를 CreaterId=userId, bookId, content로 생성
            // [!] createrId, bookId, content만 초기화시키면 되며, 다른 변수들은 자동으로 초기화됨
            Comment comment = Comment.builder()
                .createrId(userId)
                .bookId(reqDto.getBookId())
                .content(reqDto.getContent())
                .build();
            comment = Comment.repository().save(comment);
            if (comment == null) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            }
            
            // [2] CommentCreated 이벤트를 발생시킴
            CommentCreated commentCreatedEvent = new CommentCreated(comment);
            commentCreatedEvent.publish();
            
            // [3] 생성된 Comment 객체의 ID를 반환
            CreateCommentResDto resDto = new CreateCommentResDto(comment);
            CustomLogger.debugObject(CustomLoggerType.EXIT, "", resDto);
            return ResponseEntity.ok(resDto);

        } catch(Exception e) {
            CustomLogger.errorObject(e, "", reqDto);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
