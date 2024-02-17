package bookGenerator.bookShelf.viewHandler;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import bookGenerator._global.config.kafka.KafkaProcessor;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;

import bookGenerator.bookShelf.domain.BookShelf;
import bookGenerator.bookShelf.domain.BookShelfManageService;
import bookGenerator.bookShelf.event.BookCountUpdated;

@Service
public class WhenBookCountUpdated_UpdateBookCount_ViewHandler  {

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='BookCountUpdated'"
    )
    public void whenBookCountUpdated_UpdateBookCount_ViewHandler(
        @Payload BookCountUpdated bookCountUpdated
    ) {
        try {

            CustomLogger.debugObject(CustomLoggerType.ENTER, bookCountUpdated);


            BookShelf.repository().save(
                BookShelfManageService.getInstance().findByBookShelfId(bookCountUpdated.getId())
                    .copyAllProperties(bookCountUpdated)
            );


            CustomLogger.debug(CustomLoggerType.EXIT);

        } catch (Exception e) {
            CustomLogger.errorObject(e, bookCountUpdated);
        }
    }
    
}
