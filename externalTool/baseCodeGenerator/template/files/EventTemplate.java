package [[SERVICE_INFO.PACKAGE_NAME]]._global.event;

import lombok.NoArgsConstructor;

import [[SERVICE_INFO.PACKAGE_NAME]]._global.eventBase.EventNameAnnotation;
import [[SERVICE_INFO.PACKAGE_NAME]]._global.eventBase.[[TEMPLATE.NAME]]Event;
import [[SERVICE_INFO.PACKAGE_NAME]].domain.[[TEMPLATE.NAME]];

@EventNameAnnotation(eventName="[[TEMPLATE.EVENT_NAME]]")
@NoArgsConstructor
public class [[TEMPLATE.EVENT_NAME]] extends [[TEMPLATE.NAME]]Event {
    public [[TEMPLATE.EVENT_NAME]]([[TEMPLATE.NAME]] aggregate) {
        super(aggregate);
    }
}
