package [[SERVICE_INFO.PACKAGE_NAME]].policy;

import javax.transaction.Transactional;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import [[SERVICE_INFO.PACKAGE_NAME]]._global.config.kafka.KafkaProcessor;
import [[SERVICE_INFO.PACKAGE_NAME]]._global.logger.CustomLogger;
import [[SERVICE_INFO.PACKAGE_NAME]]._global.logger.CustomLoggerType;
import [[SERVICE_INFO.PACKAGE_NAME]]._global.event.[[TEMPLATE.FROM_EVENT_NAME]];

@Service
@Transactional
public class [[TEMPLATE.FROM_EVENT_NAME]]_[[TEMPLATE.POLICY_NAME]]_Policy {

    // [[TEMPLATE.FROM_EVENT_NAME]] 이벤트 발생 관련 정책
    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='[[TEMPLATE.FROM_EVENT_NAME]]'"
    )
    public void [[TEMPLATE.FROM_EVENT_NAME_FUNCTION]]_[[TEMPLATE.POLICY_NAME]]_Policy(
        @Payload [[TEMPLATE.FROM_EVENT_NAME]] [[TEMPLATE.FROM_EVENT_NAME_FUNCTION]]
    ) {
        try
        {
            
            CustomLogger.debugObject(CustomLoggerType.ENTER_EXIT, "[[TEMPLATE.FROM_EVENT_NAME]]", [[TEMPLATE.FROM_EVENT_NAME_FUNCTION]]);

        } catch(Exception e) {
            CustomLogger.errorObject(e, "", [[TEMPLATE.FROM_EVENT_NAME_FUNCTION]]);        
        }
    }

}
