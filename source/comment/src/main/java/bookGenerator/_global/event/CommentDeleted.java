package bookGenerator._global.event;

import lombok.NoArgsConstructor;

import bookGenerator._global.eventBase.EventNameAnnotation;
import bookGenerator._global.eventBase.CommentEvent;
import bookGenerator.domain.Comment;

@EventNameAnnotation(eventName="CommentDeleted")
@NoArgsConstructor
public class CommentDeleted extends CommentEvent {
    public CommentDeleted(Comment aggregate) {
        super(aggregate);
    }
}
