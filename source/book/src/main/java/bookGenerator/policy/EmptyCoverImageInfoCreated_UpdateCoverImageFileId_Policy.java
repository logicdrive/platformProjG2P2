package bookGenerator.policy;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import bookGenerator._global.config.kafka.KafkaProcessor;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;
import bookGenerator.domain.Book;
import bookGenerator.domain.BookRepository;
import bookGenerator._global.event.CoverImageFileIdUpdated;
import bookGenerator._global.event.EmptyCoverImageInfoCreated;

@Service
@Transactional
public class EmptyCoverImageInfoCreated_UpdateCoverImageFileId_Policy {

    @Autowired
    private BookRepository bookRepository;

    // E-Book 표지 이미지 파일이 생성되었을 경우, 관련 파일 ID를 업데이트하기 위해서
    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='EmptyCoverImageInfoCreated'"
    )
    public void emptyCoverImageInfoCreated_UpdateCoverImageFileId_Policy(
        @Payload EmptyCoverImageInfoCreated emptyCoverImageInfoCreated
    ) {
        try
        {
            CustomLogger.debugObject(CustomLoggerType.ENTER, "EmptyCoverImageInfoCreated", emptyCoverImageInfoCreated);
            
            // [1] bookId로 Book 객체를 찾음
            Book book = bookRepository.findById(emptyCoverImageInfoCreated.getBookId()).orElse(null);
            if (book == null) {
                throw new IllegalArgumentException("book is null");
            }

            // [2] Book 객체의 coverImageFileId를 업데이트
            book.setCoverImageFileId(emptyCoverImageInfoCreated.getId());

            // [3] Book 객체로 CoverImageFileIdUpdated 이벤트를 발생시킴
            Book coverImageFile = Book.repository().save(book);
            (new CoverImageFileIdUpdated(coverImageFile)).publish();

            CustomLogger.debug(CustomLoggerType.EXIT);

        } catch(Exception e) {
            CustomLogger.errorObject(e, "", emptyCoverImageInfoCreated);        
        }
    }

}
