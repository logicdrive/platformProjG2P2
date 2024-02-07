package bookGenerator._global.infra;

import org.springframework.beans.BeanUtils;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.util.MimeTypeUtils;

import bookGenerator.BootApplication;
import bookGenerator._global.config.kafka.KafkaProcessor;
import bookGenerator._global.eventBase.EventNameAnnotation;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;

// Kafka 이벤트들을 관리하기 위한 기반 클래스
public class AbstractEvent {
    String eventType;
    Long timestamp;

    public AbstractEvent(Object aggregate) {
        this();
        BeanUtils.copyProperties(aggregate, this);
    }

    public AbstractEvent() {
        this.setEventType(this.getClass().getAnnotation(EventNameAnnotation.class).eventName());
        this.timestamp = System.currentTimeMillis();
    }

    // 생성된 이벤트를 Kafka로 발행시키기 위해서
    public void publish() {
        KafkaProcessor processor = BootApplication.applicationContext.getBean(
            KafkaProcessor.class
        );
        MessageChannel outputChannel = processor.outboundTopic();
        
        CustomLogger.debugObject(CustomLoggerType.EFFECT, "Publish event", this);
        outputChannel.send(
            MessageBuilder
                .withPayload(this)
                .setHeader(
                    MessageHeaders.CONTENT_TYPE,
                    MimeTypeUtils.APPLICATION_JSON
                )
                .setHeader("type", getEventType())
                .build()
        );
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public boolean validate() {
        return getEventType().equals(this.getClass().getAnnotation(EventNameAnnotation.class).eventName());
    }
}
