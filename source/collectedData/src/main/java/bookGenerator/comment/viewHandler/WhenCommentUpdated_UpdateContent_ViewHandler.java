package bookGenerator.comment.viewHandler;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import bookGenerator._global.config.kafka.KafkaProcessor;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;

import bookGenerator.comment.domain.Comment;
import bookGenerator.comment.domain.CommentManageService;
import bookGenerator.comment.event.CommentUpdated;

@Service
public class WhenCommentUpdated_UpdateContent_ViewHandler {

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='CommentUpdated'"
    )
    public void whenCommentUpdated_UpdateContent_ViewHandler(
        @Payload CommentUpdated commentUpdated
    ) {
        try {

            CustomLogger.debugObject(CustomLoggerType.ENTER, commentUpdated);


            Comment.repository().save(
                CommentManageService.getInstance().findByCommentId(commentUpdated.getId())
                    .copyAllProperties(commentUpdated)
            );


            CustomLogger.debug(CustomLoggerType.EXIT);

        } catch (Exception e) {
            CustomLogger.errorObject(e, commentUpdated);
        }
    }
    
}
