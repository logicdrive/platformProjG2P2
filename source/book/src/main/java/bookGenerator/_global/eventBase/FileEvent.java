package bookGenerator._global.eventBase;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import bookGenerator._global.infra.AbstractEvent;

@Data
@ToString
@EqualsAndHashCode(callSuper=false)
public class FileEvent extends AbstractEvent {
	private Long id;
	private String url;
	private Date createdDate;
	private Date updatedDate;

    public FileEvent() {
        super();
    }
}
