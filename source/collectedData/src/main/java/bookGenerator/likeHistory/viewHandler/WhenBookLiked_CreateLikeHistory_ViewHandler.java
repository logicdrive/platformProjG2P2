package bookGenerator.likeHistory.viewHandler;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import bookGenerator._global.config.kafka.KafkaProcessor;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;

import bookGenerator.likeHistory.domain.LikeHistory;
import bookGenerator.likeHistory.event.BookLiked;

@Service
public class WhenBookLiked_CreateLikeHistory_ViewHandler {

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='BookLiked'"
    )
    public void whenBookLiked_CreateLikeHistory_ViewHandler(
        @Payload BookLiked bookLiked
    ) {
        try {

            CustomLogger.debugObject(CustomLoggerType.ENTER, bookLiked);


            LikeHistory.repository().save(
                LikeHistory.createWithObject(bookLiked)
            );


            CustomLogger.debug(CustomLoggerType.EXIT);

        } catch (Exception e) {
            CustomLogger.errorObject(e, bookLiked);
        }
    }
    
}
