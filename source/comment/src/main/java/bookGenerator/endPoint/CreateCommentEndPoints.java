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


            Comment savedComment = Comment.repository().save(
                Comment.builder()
                    .createrId(userId)
                    .bookId(reqDto.getBookId())
                    .content(reqDto.getContent())
                    .build()
            );
            (new CommentCreated(savedComment)).publish();


            CreateCommentResDto resDto = new CreateCommentResDto(savedComment);
            CustomLogger.debugObject(CustomLoggerType.EXIT, resDto);
            return ResponseEntity.ok(resDto);

        } catch(Exception e) {
            CustomLogger.errorObject(e, "", reqDto);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
