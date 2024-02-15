package bookGenerator.policy;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import bookGenerator._global.config.kafka.KafkaProcessor;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;
import bookGenerator.domain.Problem;
import bookGenerator._global.event.IndexDeleted;
import bookGenerator._global.event.ProblemDeleted;

@Service
@Transactional
public class IndexDeleted_deleteProblem_Policy {

    // Index가 삭제되었을 경우, 연관된 Problem도 함께 삭제시키기 위해서
    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='IndexDeleted'"
    )
    public void indexDeleted_deleteProblem_Policy(
        @Payload IndexDeleted indexDeleted
    ) {
        try
        {

            CustomLogger.debugObject(CustomLoggerType.ENTER, indexDeleted);
            
            // [1] indexDeleted.id를 이용해여 Problem들을 찾아서 삭제한다.
            // Problem problem = ProblemManageService.getInstance().findByIdOrThrow(indexDeleted.getId());
            List<Problem> problems = Problem.repository().findByIndexId(indexDeleted.getId());
            for (Problem problem : problems){

                Problem.repository().delete(problem);
                // [2] ProblemDeleted 이벤트를 삭제한 Content들을 기반으로 발생시킨다.
                (new ProblemDeleted(problem)).publish();
            }


            CustomLogger.debug(CustomLoggerType.EXIT);

        } catch(Exception e) {
            CustomLogger.errorObject(e, "", indexDeleted);        
        }
    }

}
