package bookGenerator._global.event;

import lombok.NoArgsConstructor;

import bookGenerator._global.eventBase.EventNameAnnotation;
import bookGenerator._global.eventBase.CommentEvent;
import bookGenerator.domain.Comment;

@NoArgsConstructor
@EventNameAnnotation(eventName="CommentCreated")
public class CommentCreated extends CommentEvent {
    public CommentCreated(Comment aggregate) {
        super(aggregate);
    }
}
