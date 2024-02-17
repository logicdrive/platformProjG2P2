package bookGenerator.book.viewHandler;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import bookGenerator._global.config.kafka.KafkaProcessor;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;

import bookGenerator.book.domain.Book;
import bookGenerator.book.domain.BookManageService;
import bookGenerator.book.event.BookIsSharedUpdated;

@Service
public class WhenBookIsSharedUpdated_UpdateIsShared_ViewHandler {

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='BookIsSharedUpdated'"
    )
    public void whenBookIsSharedUpdated_UpdateIsShared_ViewHandler(
        @Payload BookIsSharedUpdated bookIsSharedUpdated
    ) {
        try {

            CustomLogger.debugObject(CustomLoggerType.ENTER, bookIsSharedUpdated);


            Book.repository().save(
                BookManageService.getInstance().findByBookId(bookIsSharedUpdated.getId())
                    .copyAllProperties(bookIsSharedUpdated)
            );


            CustomLogger.debug(CustomLoggerType.EXIT);

        } catch (Exception e) {
            CustomLogger.errorObject(e, bookIsSharedUpdated);
        }
    }
    
}
