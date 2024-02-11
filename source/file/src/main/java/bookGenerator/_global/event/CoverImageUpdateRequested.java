package bookGenerator._global.event;

import lombok.Data;
import lombok.EqualsAndHashCode;

import bookGenerator._global.eventBase.BookEvent;
import bookGenerator._global.eventBase.EventNameAnnotation;


@Data
@EqualsAndHashCode(callSuper=false)
@EventNameAnnotation(eventName="CoverImageUpdateRequested")
public class CoverImageUpdateRequested extends BookEvent {
    private String imageUrl;

    public CoverImageUpdateRequested() {
        super();
    }

    public String toString() {
        return String.format("%s(bookId=%d, imageUrlLength=%d)",
            this.getClass().getSimpleName(), this.id, this.imageUrl.length());
    }
}
