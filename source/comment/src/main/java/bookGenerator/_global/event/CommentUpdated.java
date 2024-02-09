package bookGenerator._global.event;

import lombok.NoArgsConstructor;

import bookGenerator._global.eventBase.EventNameAnnotation;
import bookGenerator._global.eventBase.CommentEvent;
import bookGenerator.domain.Comment;

@EventNameAnnotation(eventName="CommentUpdated")
@NoArgsConstructor
public class CommentUpdated extends CommentEvent {
    public CommentUpdated(Comment aggregate) {
        super(aggregate);
    }
}
