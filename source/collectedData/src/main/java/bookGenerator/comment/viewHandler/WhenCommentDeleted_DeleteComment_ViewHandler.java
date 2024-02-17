package bookGenerator.comment.viewHandler;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import bookGenerator._global.config.kafka.KafkaProcessor;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;

import bookGenerator.comment.domain.Comment;
import bookGenerator.comment.domain.CommentManageService;
import bookGenerator.comment.event.CommentDeleted;

@Service
public class WhenCommentDeleted_DeleteComment_ViewHandler {

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='CommentDeleted'"
    )
    public void whenCommentDeleted_DeleteComment_ViewHandler(
        @Payload CommentDeleted commentDeleted
    ) {
        try {

            CustomLogger.debugObject(CustomLoggerType.ENTER, commentDeleted);


            Comment.repository().delete(
                CommentManageService.getInstance().findByCommentId(commentDeleted.getId())
            );


            CustomLogger.debug(CustomLoggerType.EXIT);

        } catch (Exception e) {
            CustomLogger.errorObject(e, commentDeleted);
        }
    }
    
}
