package bookGenerator.content.event;

import lombok.NoArgsConstructor;

import bookGenerator._global.eventBase.EventNameAnnotation;
import bookGenerator._global.eventBase.ContentEvent;

@NoArgsConstructor
@EventNameAnnotation(eventName="ContentUpdated")
public class ContentUpdated extends ContentEvent {
}
