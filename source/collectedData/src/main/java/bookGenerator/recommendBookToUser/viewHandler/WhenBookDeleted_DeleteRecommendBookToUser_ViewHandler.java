package bookGenerator.recommendBookToUser.viewHandler;

import javax.transaction.Transactional;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import bookGenerator._global.config.kafka.KafkaProcessor;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;

import bookGenerator.book.event.BookDeleted;
import bookGenerator.recommendBookToUser.domain.RecommendBookToUser;

@Service
public class WhenBookDeleted_DeleteRecommendBookToUser_ViewHandler {

    @Transactional
    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='BookDeleted'"
    )
    public void whenBookDeleted_DeleteRecommendBookToUser_ViewHandler(
        @Payload BookDeleted bookDeleted
    ) {
        try {

            CustomLogger.debugObject(CustomLoggerType.ENTER, bookDeleted);


            RecommendBookToUser.repository().deleteByRecommendedBookId(bookDeleted.getId());


            CustomLogger.debug(CustomLoggerType.EXIT);

        } catch (Exception e) {
            CustomLogger.errorObject(e, bookDeleted);
        }
    }
    
}
