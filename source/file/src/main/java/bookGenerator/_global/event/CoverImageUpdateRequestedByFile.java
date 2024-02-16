package bookGenerator._global.event;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import bookGenerator._global.eventBase.EventNameAnnotation;
import bookGenerator._global.eventBase.FileEvent;
import bookGenerator.domain.File;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
@EventNameAnnotation(eventName="CoverImageGenerationRequestedByFile")
public class CoverImageUpdateRequestedByFile extends FileEvent {
    private String imageUrl;

    public CoverImageUpdateRequestedByFile(File file) {
        super(file);
    }

    public String toString() {
        return String.format("%s(super=%s, imageUrlLength=%d)",
            this.getClass().getSimpleName(), super.toString(), this.imageUrl.length());
    }
}
