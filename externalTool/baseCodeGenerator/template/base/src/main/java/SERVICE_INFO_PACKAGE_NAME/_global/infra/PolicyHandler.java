package [[SERVICE_INFO.PACKAGE_NAME]]._global.infra;

import javax.transaction.Transactional;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import [[SERVICE_INFO.PACKAGE_NAME]]._global.config.kafka.KafkaProcessor;

@Service
@Transactional
public class PolicyHandler {
    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}
}
