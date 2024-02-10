package bookGenerator._global.event;

import lombok.NoArgsConstructor;

import bookGenerator._global.eventBase.EventNameAnnotation;
import bookGenerator._global.eventBase.BookEvent;
import bookGenerator.domain.Book;

@EventNameAnnotation(eventName="CoverImageUpdateRequested")
@NoArgsConstructor
public class CoverImageUpdateRequested extends BookEvent {
    private String imageUrl;

    public CoverImageUpdateRequested(Book aggregate, String imageUrl) {
        super(aggregate);
        this.imageUrl = imageUrl;
    }

    public String toString() {
        return String.format("%s(bookId=%d, imageUrlLength=%d)",
            this.getClass().getSimpleName(), this.id, this.imageUrl.length());
    }
}
