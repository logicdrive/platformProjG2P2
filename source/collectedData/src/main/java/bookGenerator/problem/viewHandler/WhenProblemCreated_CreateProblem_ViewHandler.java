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
import bookGenerator.problem.event.ProblemCreated;
import bookGenerator.webSocket.WebSocketEventHandler;

@Service
public class WhenProblemCreated_CreateProblem_ViewHandler {

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='ProblemCreated'"
    )
    public void whenProblemCreated_CreateProblem_ViewHandler(
        @Payload ProblemCreated problemCreated
    ) {
        try {

            CustomLogger.debugObject(CustomLoggerType.ENTER, problemCreated);


            Problem.repository().save(
                Problem.createWithObject(problemCreated)
            );


            CustomLogger.debug(CustomLoggerType.EXIT);
            WebSocketEventHandler.getInstance().notifyEventsToSpecificUser(
                BookManageService.getInstance().findByBookId(
                    IndexManageService.getInstance().findByIndexId(problemCreated.getIndexId()).getBookId()
                ).getCreaterId(), 
                "ProblemCreated", 
                String.format("{\"problemId\": %d}", problemCreated.getId())
            );

        } catch (Exception e) {
            CustomLogger.errorObject(e, problemCreated);
        }
    }
    
}
