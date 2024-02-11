package bookGenerator._global.event;

import lombok.Data;
import lombok.EqualsAndHashCode;

import bookGenerator._global.eventBase.EventNameAnnotation;
import bookGenerator._global.eventBase.FileEvent;


@Data
@EqualsAndHashCode(callSuper=false)
@EventNameAnnotation(eventName="CoverImageGenerationRequestedByFile")
public class CoverImageUpdateRequestedByFile extends FileEvent {
    private String imageUrl;

    public CoverImageUpdateRequestedByFile() {
        super();
    }

    public String toString() {
        return String.format("%s(fileId=%d, imageUrlLength=%d)",
            this.getClass().getSimpleName(), this.id, this.imageUrl.length());
    }
}
