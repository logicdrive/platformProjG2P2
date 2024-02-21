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
import bookGenerator.problem.event.ProblemDeleted;
import bookGenerator.webSocket.WebSocketEventHandler;

@Service
public class WhenProblemDeleted_DeleteProblem_ViewHandler {

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='ProblemDeleted'"
    )
    public void whenProblemDeleted_DeleteProblem_ViewHandler(
        @Payload ProblemDeleted problemDeleted
    ) {
        try {

            CustomLogger.debugObject(CustomLoggerType.ENTER, problemDeleted);


            Problem.repository().delete(
                ProblemManageService.getInstance().findByProblemId(problemDeleted.getId())
            );


            CustomLogger.debug(CustomLoggerType.EXIT);
            try {

                WebSocketEventHandler.getInstance().notifyEventsToSpecificUser(
                    BookManageService.getInstance().findByBookId(
                        IndexManageService.getInstance().findByIndexId(problemDeleted.getIndexId()).getBookId()
                    ).getCreaterId(), 
                    "ProblemDeleted", 
                    String.format("{\"problemId\": %d}", problemDeleted.getId())
                );

            } catch (Exception e) {
                CustomLogger.debug(CustomLoggerType.EFFECT, e.getMessage());
            }

        } catch (Exception e) {
            CustomLogger.errorObject(e, problemDeleted);
        }
    }
    
}
