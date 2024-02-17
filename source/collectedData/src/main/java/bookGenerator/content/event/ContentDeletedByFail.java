package bookGenerator.content.event;

import lombok.NoArgsConstructor;

import bookGenerator._global.eventBase.EventNameAnnotation;
import bookGenerator._global.eventBase.ContentEvent;

@NoArgsConstructor
@EventNameAnnotation(eventName="ContentDeletedByFail")
public class ContentDeletedByFail extends ContentEvent {
}
