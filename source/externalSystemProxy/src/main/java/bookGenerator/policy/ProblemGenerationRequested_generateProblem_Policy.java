package bookGenerator.policy;

import javax.transaction.Transactional;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import bookGenerator._global.config.kafka.KafkaProcessor;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;
import bookGenerator._global.event.ProblemGenerationFailed;
import bookGenerator._global.event.ProblemGenerationRequested;
import bookGenerator._global.event.ProblemGenerated;
import bookGenerator._global.externalSystemProxy.openai.generateProblems.ExternalSystemProxy_GenerateProblems;
import bookGenerator._global.externalSystemProxy.openai.generateProblems.GenerateProblemsReqDto;
import bookGenerator._global.externalSystemProxy.openai.generateProblems.GenerateProblemsResDto;

@Service
@Transactional
public class ProblemGenerationRequested_generateProblem_Policy {

    // ProblemGenerationRequsted 이벤트 발생 관련 정책
    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='ProblemGenerationRequested'"
    )
    public void problemGenerationRequested_generateProblem_Policy(
        @Payload ProblemGenerationRequested problemGenerationRequested
    ) {
        try
        {
                
            CustomLogger.debugObject(CustomLoggerType.ENTER, problemGenerationRequested);


            GenerateProblemsReqDto reqDto = new GenerateProblemsReqDto(problemGenerationRequested.getQuery());
            GenerateProblemsResDto resDto = ExternalSystemProxy_GenerateProblems.getInstance().externalSystemProxy_GenerateProblems(reqDto);

            for(int i = 0; i < resDto.getContents().size(); i++) {
                String content = resDto.getContents().get(i);
                String answer = resDto.getAnswers().get(i);
                (new ProblemGenerated(problemGenerationRequested.getIndexId(), content, answer, Long.valueOf(i+1))).publish();
            }

            
            CustomLogger.debug(CustomLoggerType.EXIT);
            
        } catch(Exception e) {
            CustomLogger.errorObject(e, "", problemGenerationRequested); 
            
            (new ProblemGenerationFailed(problemGenerationRequested.getIndexId())).publish();
        }
    }

}
