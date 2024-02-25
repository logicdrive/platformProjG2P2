package bookGenerator.likeHistory.viewHandler;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import bookGenerator._global.config.kafka.KafkaProcessor;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;

import bookGenerator.book.event.BookDeleted;
import bookGenerator.likeHistory.domain.LikeHistory;

@Service
public class WhenBookDeleted_DeleteLikeHistory_ViewHandler {

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='BookDeleted'"
    )
    public void whenBookDeleted_DeleteLikeHistory_ViewHandler(
        @Payload BookDeleted bookDeleted
    ) {
        try {

            CustomLogger.debugObject(CustomLoggerType.ENTER, bookDeleted);


            LikeHistory.repository().findByBookId(bookDeleted.getId(), null).forEach(
                likeHistory -> {
                    LikeHistory.repository().delete(likeHistory);
                }
            );


            CustomLogger.debug(CustomLoggerType.EXIT);

        } catch (Exception e) {
            CustomLogger.errorObject(e, bookDeleted);
        }
    }
    
}
