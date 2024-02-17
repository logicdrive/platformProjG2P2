package bookGenerator.index.viewHandler;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import bookGenerator._global.config.kafka.KafkaProcessor;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;

import bookGenerator.index.domain.Index;
import bookGenerator.index.domain.IndexManageService;
import bookGenerator.index.event.IndexDeleted;

@Service
public class WhenIndexDeleted_DeleteIndex_ViewHandler {

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='IndexDeleted'"
    )
    public void whenIndexDeleted_DeleteIndex_ViewHandler(
        @Payload IndexDeleted indexDeleted
    ) {
        try {

            CustomLogger.debugObject(CustomLoggerType.ENTER, indexDeleted);


            Index.repository().delete(
                IndexManageService.getInstance().findByIndexId(indexDeleted.getId())
            );


            CustomLogger.debug(CustomLoggerType.EXIT);

        } catch (Exception e) {
            CustomLogger.errorObject(e, indexDeleted);
        }
    }
    
}
