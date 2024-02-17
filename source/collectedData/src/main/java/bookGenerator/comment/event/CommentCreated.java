package bookGenerator.comment.event;

import lombok.NoArgsConstructor;

import bookGenerator._global.eventBase.EventNameAnnotation;
import bookGenerator._global.eventBase.CommentEvent;

@NoArgsConstructor
@EventNameAnnotation(eventName="CommentCreated")
public class CommentCreated extends CommentEvent {
}
