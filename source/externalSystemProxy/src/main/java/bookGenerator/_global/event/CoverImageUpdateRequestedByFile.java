package bookGenerator._global.event;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import bookGenerator._global.eventBase.EventNameAnnotation;
import bookGenerator._global.eventBase.FileEvent;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
@EventNameAnnotation(eventName="CoverImageUpdateRequestedByFile")
public class CoverImageUpdateRequestedByFile extends FileEvent {
    private String imageUrl;

    public String toString() {
        return String.format("%s(super=%s, imageUrlLength=%d)",
            this.getClass().getSimpleName(), super.toString(), this.imageUrl.length());
    }
}
