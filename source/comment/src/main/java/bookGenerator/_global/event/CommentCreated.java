package bookGenerator._global.event;

import lombok.NoArgsConstructor;

import bookGenerator._global.eventBase.EventNameAnnotation;
import bookGenerator._global.eventBase.CommentEvent;
import bookGenerator.domain.Comment;

@EventNameAnnotation(eventName="CommentCreated")
@NoArgsConstructor
public class CommentCreated extends CommentEvent {
    public CommentCreated(Comment aggregate) {
        super(aggregate);
    }
}
