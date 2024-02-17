package bookGenerator.book.viewHandler;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import bookGenerator._global.config.kafka.KafkaProcessor;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;

import bookGenerator.book.domain.Book;
import bookGenerator.book.domain.BookManageService;
import bookGenerator.book.event.CoverImageFileIdUpdated;

@Service
public class WhenCoverImageFileIdUpdated_UpdateFileId_ViewHandler {

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='CoverImageFileIdUpdated'"
    )
    public void whenCoverImageFileIdUpdated_UpdateFileId_ViewHandler(
        @Payload CoverImageFileIdUpdated coverImageFileIdUpdated
    ) {
        try {

            CustomLogger.debugObject(CustomLoggerType.ENTER, coverImageFileIdUpdated);


            Book.repository().save(
                BookManageService.getInstance().findByBookId(coverImageFileIdUpdated.getId())
                    .copyAllProperties(coverImageFileIdUpdated)
            );


            CustomLogger.debug(CustomLoggerType.EXIT);

        } catch (Exception e) {
            CustomLogger.errorObject(e, coverImageFileIdUpdated);
        }
    }
    
}
