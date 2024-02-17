package bookGenerator.index.event;

import lombok.NoArgsConstructor;

import bookGenerator._global.eventBase.EventNameAnnotation;
import bookGenerator._global.eventBase.IndexEvent;

@NoArgsConstructor
@EventNameAnnotation(eventName="IndexCreated")
public class IndexCreated extends IndexEvent {
}
