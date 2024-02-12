package bookGenerator._global.event;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import bookGenerator._global.eventBase.EventNameAnnotation;
import bookGenerator._global.eventBase.BookEvent;
import bookGenerator.domain.Book;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
@EventNameAnnotation(eventName="CoverImageUpdateRequested")
public class CoverImageUpdateRequested extends BookEvent {
    private String imageUrl;

    public CoverImageUpdateRequested(Book aggregate, String imageUrl) {
        super(aggregate);
        this.imageUrl = imageUrl;
    }

    public String toString() {
        return String.format("%s(super=%s, imageUrlLength=%d)",
            this.getClass().getSimpleName(), super.toString(), this.imageUrl.length());
    }
}
