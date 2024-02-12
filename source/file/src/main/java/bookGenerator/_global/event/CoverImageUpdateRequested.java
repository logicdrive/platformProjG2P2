package bookGenerator._global.event;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import bookGenerator._global.eventBase.BookEvent;
import bookGenerator._global.eventBase.EventNameAnnotation;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
@EventNameAnnotation(eventName="CoverImageUpdateRequested")
public class CoverImageUpdateRequested extends BookEvent {
    private String imageUrl;

    public String toString() {
        return String.format("%s(super=%s, imageUrlLength=%d)",
            this.getClass().getSimpleName(), super.toString(), this.imageUrl.length());
    }
}
