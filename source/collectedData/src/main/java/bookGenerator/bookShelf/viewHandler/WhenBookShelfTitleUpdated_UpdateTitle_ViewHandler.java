package bookGenerator.bookShelf.viewHandler;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import bookGenerator._global.config.kafka.KafkaProcessor;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;

import bookGenerator.bookShelf.domain.BookShelf;
import bookGenerator.bookShelf.domain.BookShelfManageService;
import bookGenerator.bookShelf.event.BookShelfTitleUpdated;

@Service
public class WhenBookShelfTitleUpdated_UpdateTitle_ViewHandler  {

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='BookShelfTitleUpdated'"
    )
    public void whenBookShelfTitleUpdated_UpdateTitle_ViewHandler(
        @Payload BookShelfTitleUpdated bookShelfTitleUpdated
    ) {
        try {

            CustomLogger.debugObject(CustomLoggerType.ENTER, bookShelfTitleUpdated);


            BookShelf.repository().save(
                BookShelfManageService.getInstance().findByBookShelfId(bookShelfTitleUpdated.getId())
                    .copyAllProperties(bookShelfTitleUpdated)
            );


            CustomLogger.debug(CustomLoggerType.EXIT);

        } catch (Exception e) {
            CustomLogger.errorObject(e, bookShelfTitleUpdated);
        }
    }
    
}
