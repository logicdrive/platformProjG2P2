package bookGenerator.tag.event;

import lombok.NoArgsConstructor;

import bookGenerator._global.eventBase.EventNameAnnotation;
import bookGenerator._global.eventBase.TagEvent;

@NoArgsConstructor
@EventNameAnnotation(eventName="TagCreated")
public class TagCreated extends TagEvent {
}
