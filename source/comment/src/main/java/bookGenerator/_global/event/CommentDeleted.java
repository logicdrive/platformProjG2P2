package bookGenerator._global.event;

import lombok.NoArgsConstructor;

import bookGenerator._global.eventBase.EventNameAnnotation;
import bookGenerator._global.eventBase.CommentEvent;
import bookGenerator.domain.Comment;

@NoArgsConstructor
@EventNameAnnotation(eventName="CommentDeleted")
public class CommentDeleted extends CommentEvent {
    public CommentDeleted(Comment aggregate) {
        super(aggregate);
    }
}
