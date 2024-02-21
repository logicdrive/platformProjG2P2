package bookGenerator.problem.viewHandler;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import bookGenerator._global.config.kafka.KafkaProcessor;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;
import bookGenerator.book.domain.BookManageService;
import bookGenerator.index.domain.IndexManageService;
import bookGenerator.problem.domain.Problem;
import bookGenerator.problem.domain.ProblemManageService;
import bookGenerator.problem.event.ProblemDeletedByFail;
import bookGenerator.webSocket.WebSocketEventHandler;

@Service
public class WhenProblemDeletedByFail_DeleteProblem_ViewHandler {

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='ProblemDeletedByFail'"
    )
    public void whenProblemDeletedByFail_DeleteProblem_ViewHandler(
        @Payload ProblemDeletedByFail problemDeletedByFail
    ) {
        try {

            CustomLogger.debugObject(CustomLoggerType.ENTER, problemDeletedByFail);


            Problem problemToDelete = ProblemManageService.getInstance().findByProblemId(problemDeletedByFail.getProblemId());
            Problem.repository().delete(problemToDelete);


            CustomLogger.debug(CustomLoggerType.EXIT);
            WebSocketEventHandler.getInstance().notifyEventsToSpecificUser(
                BookManageService.getInstance().findByBookId(
                    IndexManageService.getInstance().findByIndexId(
                        problemToDelete.getIndexId()
                    ).getBookId()
                ).getCreaterId(), 
                "ProblemDeletedByFail", 
                String.format("{\"problemId\": %d}", problemDeletedByFail.getProblemId())
            );


        } catch (Exception e) {
            CustomLogger.errorObject(e, problemDeletedByFail);
        }
    }
    
}
