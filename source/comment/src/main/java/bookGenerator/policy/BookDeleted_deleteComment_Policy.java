package bookGenerator.policy;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import bookGenerator.domain.Comment;
import bookGenerator.domain.CommentRepository;
import bookGenerator._global.config.kafka.KafkaProcessor;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;
import bookGenerator._global.event.BookDeleted;
import bookGenerator._global.eventBase.CommentEvent;

@Service
@Transactional
public class BookDeleted_deleteComment_Policy {
    private final CommentRepository commentRepository;
    private final KafkaProcessor kafkaProcessor;

    public BookDeleted_deleteComment_Policy(CommentRepository commentRepository, KafkaProcessor kafkaProcessor) {
        this.commentRepository = commentRepository;
        this.kafkaProcessor = kafkaProcessor;

    }

    // 책이 삭제되었을 경우, 생성된 코멘트들을 삭제하기 위한 정책
    @StreamListener(value = KafkaProcessor.INPUT, condition = "headers['type']=='BookDeleted'")
    public void bookDeleted_deleteComment_Policy(
            @Payload BookDeleted bookDeleted) {
        try {

            CustomLogger.debugObject(CustomLoggerType.ENTER, bookDeleted);

            // [1] 해당 책과 관련된 Comment들을 삭제
            List<Comment> comments = commentRepository.findByBookId(bookDeleted.getBookId());
            for (Comment comment : comments) {
                commentRepository.delete(comment);

                // [2] 각각의 삭제된 Comment들에 대해서 CommentDeleted 이벤트를 발생시킴
                CommentEvent commentDeletedEvent = new CommentEvent();
                commentDeletedEvent.setBookId(comment.getBookId());
                commentDeletedEvent.setId(comment.getId());
                kafkaProcessor.send(commentDeletedEvent);

            }

            CustomLogger.debug(CustomLoggerType.EXIT);

        } catch (Exception e) {
            CustomLogger.errorObject(e, "", bookDeleted);
        }

    }
}
