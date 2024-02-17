package bookGenerator.bookShelf.viewHandler;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import bookGenerator._global.config.kafka.KafkaProcessor;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;

import bookGenerator.bookShelf.domain.BookShelf;
import bookGenerator.bookShelf.domain.BookShelfManageService;
import bookGenerator.bookShelf.event.BookShelfIsSharedUpdated;

@Service
public class WhenBookShelfIsSharedUpdated_UpdateIsShared_ViewHandler {

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='BookShelfIsSharedUpdated'"
    )
    public void whenBookShelfIsSharedUpdated_UpdateIsShared_ViewHandler(
        @Payload BookShelfIsSharedUpdated bookShelfIsSharedUpdated
    ) {
        try {

            CustomLogger.debugObject(CustomLoggerType.ENTER, bookShelfIsSharedUpdated);


            BookShelf.repository().save(
                BookShelfManageService.getInstance().findByBookShelfId(bookShelfIsSharedUpdated.getId())
                    .copyAllProperties(bookShelfIsSharedUpdated)
            );


            CustomLogger.debug(CustomLoggerType.EXIT);

        } catch (Exception e) {
            CustomLogger.errorObject(e, bookShelfIsSharedUpdated);
        }
    }
    
}
