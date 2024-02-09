package [[SERVICE_INFO.PACKAGE_NAME]]._global.event;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import [[SERVICE_INFO.PACKAGE_NAME]]._global.eventBase.EventNameAnnotation;
import [[SERVICE_INFO.PACKAGE_NAME]]._global.infra.AbstractEvent;

@Data
@ToString
@EqualsAndHashCode(callSuper=false)
@EventNameAnnotation(eventName="[[TEMPLATE.FROM_EVENT_NAME]]")
public class [[TEMPLATE.FROM_EVENT_NAME]] extends AbstractEvent {
[[TEMPLATE.FROM_EVENT_ATTRIBUTE]]

    public [[TEMPLATE.FROM_EVENT_NAME]]() {
        super();
    }
}
