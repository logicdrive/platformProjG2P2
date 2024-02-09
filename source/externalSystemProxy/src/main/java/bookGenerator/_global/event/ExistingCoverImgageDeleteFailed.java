package bookGenerator._global.event;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import bookGenerator._global.eventBase.EventNameAnnotation;
import bookGenerator._global.infra.AbstractEvent;

@Data
@ToString
@EqualsAndHashCode(callSuper=false)
@EventNameAnnotation(eventName="ExistingCoverImgageDeleteFailed")
public class ExistingCoverImgageDeleteFailed extends AbstractEvent {
	private Long id;
	private Long fileId;

    public ExistingCoverImgageDeleteFailed() {
        super();
    }
}
