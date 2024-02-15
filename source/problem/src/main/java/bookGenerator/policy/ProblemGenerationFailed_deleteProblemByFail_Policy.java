package bookGenerator.policy;

import javax.transaction.Transactional;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import bookGenerator._global.config.kafka.KafkaProcessor;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;
import bookGenerator._global.event.ProblemDeletedByFail;
import bookGenerator._global.event.ProblemGenerationFailed;

@Service
@Transactional
public class ProblemGenerationFailed_deleteProblemByFail_Policy {
    // 문제 생성에 실패 관련 이벤트를 발생시키는 정책
    @StreamListener(value = KafkaProcessor.INPUT, condition = "headers['type']=='ProblemGenerationFailed'")
    public void problemGenerationFailed_deleteProblemByFail_Policy(
            @Payload ProblemGenerationFailed problemGenerationFailed) {
        try {

            if (problemGenerationFailed == null) {
                throw new IllegalArgumentException("problemGenerationFailed is null");
            }

            CustomLogger.debugObject(CustomLoggerType.ENTER, problemGenerationFailed);

            // [1] problemGenerationFailed.problemId으로 ProblemDeletedByFail 이벤트를 발생시킨다.
            // [!] 따로 문제를 삭제시킬 필요는 없다.
            ProblemDeletedByFail problemDeletedByFailEvent = new ProblemDeletedByFail(
                    problemGenerationFailed.getProblemId());

            problemDeletedByFailEvent.publish();

            CustomLogger.debug(CustomLoggerType.EXIT);

        } catch (Exception e) {
            CustomLogger.errorObject(e, "", problemGenerationFailed);
        }
    }

}
