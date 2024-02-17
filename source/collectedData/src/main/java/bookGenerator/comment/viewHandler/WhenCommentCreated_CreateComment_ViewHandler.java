package bookGenerator.comment.viewHandler;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import bookGenerator._global.config.kafka.KafkaProcessor;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;

import bookGenerator.comment.domain.Comment;
import bookGenerator.comment.event.CommentCreated;

@Service
public class WhenCommentCreated_CreateComment_ViewHandler {

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='CommentCreated'"
    )
    public void whenCommentCreated_CreateComment_ViewHandler(
        @Payload CommentCreated commentCreated
    ) {
        try {

            CustomLogger.debugObject(CustomLoggerType.ENTER, commentCreated);


            Comment.repository().save(
                Comment.createWithObject(commentCreated)
            );


            CustomLogger.debug(CustomLoggerType.EXIT);

        } catch (Exception e) {
            CustomLogger.errorObject(e, commentCreated);
        }
    }
    
}
