package bookGenerator.policy;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import bookGenerator._global.config.kafka.KafkaProcessor;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;
import bookGenerator._global.event.BookDeleted;
import bookGenerator._global.event.CommentDeleted;
import bookGenerator.domain.Comment;
import bookGenerator.domain.CommentRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;

@Service
@Transactional
public class BookDeleted_deleteComment_Policy implements ApplicationEventPublisherAware {

    @Autowired
    private CommentRepository commentRepository;
    private ApplicationEventPublisher eventPublisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher eventPublisher) {
        if (eventPublisher == null) {
            throw new IllegalArgumentException("eventPublisher cannot be null");
        }
        this.eventPublisher = eventPublisher;
    }

    // 책이 삭제되었을 경우, 생성된 코멘트들을 삭제하기 위한 정책
    @StreamListener(value = KafkaProcessor.INPUT, condition = "headers['type']=='BookDeleted'")
    public void bookDeleted_deleteComment_Policy(
            @Payload BookDeleted bookDeleted) {
        try {
            if (bookDeleted == null) {
                throw new IllegalArgumentException("bookDeleted is null");
            }

            CustomLogger.debugObject(CustomLoggerType.ENTER, bookDeleted);

            // [1] 해당 책과 관련된 Comment들을 삭제
            List<Comment> comments = commentRepository.findByBookId(bookDeleted.getBookId());
            if (comments == null) {
                throw new IllegalArgumentException("comments is null");
            }

            List<Comment> deletedComments = new ArrayList<>(comments);
            for (Comment comment : comments) {
                if (comment == null) {
                    throw new IllegalArgumentException("comment is null");
                }
                commentRepository.delete(comment);
            }

            // [2] 각각의 삭제된 Comment들에 대해서 CommentDeleted 이벤트를 발생시킴
            for (Comment comment : deletedComments) {
                if (comment == null) {
                    throw new IllegalArgumentException("comment is null");
                }
                CommentDeleted commentDeleted = new CommentDeleted(comment);
                eventPublisher.publishEvent(commentDeleted);
            }

            CustomLogger.debug(CustomLoggerType.EXIT);

        } catch (Exception e) {
            CustomLogger.errorObject(e, "", bookDeleted);
        }
    }
}
