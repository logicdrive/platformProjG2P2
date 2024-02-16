package bookGenerator.user.viewHandler;

import java.util.Optional;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import bookGenerator._global.config.kafka.KafkaProcessor;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;
import bookGenerator.user.event.SignUpCompleted;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WhenSignUpCompleted_CreateUser_ViewHandler {

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='SignUpCompleted'"
    )
    public void whenSignUpCompleted_CreateUser_ViewHandler(
        @Payload SignUpCompleted signUpCompleted
    ) {
        try {

            CustomLogger.debugObject(CustomLoggerType.ENTER, signUpCompleted);

            CustomLogger.debug(CustomLoggerType.ENTER, "", String.format("{%s: %s}", commentCreated.getClass().getSimpleName(), commentCreated.toString()));
            if (!commentCreated.validate()) return;

            Comment savedComment = this.commentRepository.save(
                Comment.builder()
                    .commentId(commentCreated.getId())
                    .createrId(commentCreated.getCreaterId())
                    .musicId(commentCreated.getMusicId())
                    .content(commentCreated.getContent())
                    .createdDate(commentCreated.getCreatedDate())
                    .updatedDate(commentCreated.getUpdatedDate())
                    .status("CommentCreated")
                    .build()
            );

            CustomLogger.debugObject(CustomLoggerType.EXIT, signUpCompleted);

        } catch (Exception e) {
            CustomLogger.errorObject(e, signUpCompleted);
        }
    }
    
}
