package bookGenerator.policy;

import javax.transaction.Transactional;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import bookGenerator._global.config.kafka.KafkaProcessor;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;
import bookGenerator.domain.BookShelf;
import bookGenerator.domain.BookShelfRepository;
import bookGenerator._global.event.BookCountUpdated;
import bookGenerator._global.event.BookShelfBookDeleted;

@Service
@Transactional
public class BookShelfBookDeleted_decreaseBookCount_Policy {

    private final BookShelfRepository bookShelfRepository;
    private final ApplicationEventPublisher eventPublisher;

    public BookShelfBookDeleted_decreaseBookCount_Policy(BookShelfRepository bookShelfRepository,
            ApplicationEventPublisher eventPublisher) {
        if (bookShelfRepository == null || eventPublisher == null) {
            throw new IllegalArgumentException("bookShelfRepository or eventPublisher cannot be null");
        }
        this.bookShelfRepository = bookShelfRepository;
        this.eventPublisher = eventPublisher;
    }

    // BookShelf에 기존의 책이 삭제되었을 경우, 개수를 내리는 정책
    @StreamListener(value = KafkaProcessor.INPUT, condition = "headers['type']=='BookShelfBookDeleted'")
    public void bookShelfBookDeleted_decreaseBookCount_Policy(
            @Payload BookShelfBookDeleted bookShelfBookDeleted) {
        try {

            if (bookShelfBookDeleted == null) {
                throw new IllegalArgumentException("bookShelfBookDeleted is null");
            }

            CustomLogger.debugObject(CustomLoggerType.ENTER, bookShelfBookDeleted);
            // [1] bookShelfId로 BookShelf를 조회함
            BookShelf bookShelf = bookShelfRepository.findById(bookShelfBookDeleted.getBookShelfId())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid bookShelfId"));

            if (bookShelf == null) {
                throw new IllegalArgumentException("bookShelf is null");
            }

            // [2] 조회된 BookShelf의 bookCount를 1 내림
            bookShelf.decreaseBookCount();
            // [3] BookCountUpdated 이벤트를 저장시킨 BookShelf로 발생시킴
            BookCountUpdated event = new BookCountUpdated(bookShelf);
            eventPublisher.publishEvent(event);
            CustomLogger.debug(CustomLoggerType.EXIT);

        } catch (Exception e) {
            CustomLogger.errorObject(e, "", bookShelfBookDeleted);
        }
    }

}
