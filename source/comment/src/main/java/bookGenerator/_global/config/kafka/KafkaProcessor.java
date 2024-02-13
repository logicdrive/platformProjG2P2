package bookGenerator._global.config.kafka;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.messaging.support.MessageBuilder;

import bookGenerator._global.eventBase.CommentEvent;

// Kafka와 관련된 기본 세팅 및 통신을 위해서 필요한 객체
public interface KafkaProcessor {
    String INPUT = "event-in";
    String OUTPUT = "event-out";

    @Input(INPUT)
    SubscribableChannel inboundTopic();

    @Output(OUTPUT)
    MessageChannel outboundTopic();
    default void send(CommentEvent commentEvent) {
        Message<CommentEvent> message = MessageBuilder.withPayload(commentEvent).build();
        outboundTopic().send(message);
    }
}
