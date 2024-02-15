package bookGenerator.policy;

import javax.transaction.Transactional;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import bookGenerator._global.config.kafka.KafkaProcessor;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;
import bookGenerator.domain.File;
import bookGenerator.domain.FileManageService;
import bookGenerator._global.event.BookDeleted;
import bookGenerator._global.event.FileInfoDeleted;

@Service
@Transactional
public class BookDeleted_DeleteFileInfo_Policy {

    // 책이 삭제되었을 경우, 해당 책의 Cover Image도 삭제시키는 정책
    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='BookDeleted'"
    )
    public void bookDeleted_DeleteFileInfo_Policy(
        @Payload BookDeleted bookDeleted
    ) {
        try
        {
            
            CustomLogger.debugObject(CustomLoggerType.ENTER, bookDeleted);

            // [1] bookDeleted.coverImageFileId에 해당하는 File의 Id를 조회한다.
            File fileToDelete = FileManageService.getInstance().findByIdOrThrow(bookDeleted.getCoverImageFileId());
            
            // [2] 조회된 File을 삭제한다.
            File.repository().delete(fileToDelete);

            // [3] 삭제된 File로 FileInfoDeleted 이벤트를 발생시킨다.
            (new FileInfoDeleted(fileToDelete)).publish();
            
            CustomLogger.debug(CustomLoggerType.EXIT);

        } catch(Exception e) {
            CustomLogger.errorObject(e, "", bookDeleted);        
        }
    }

}
