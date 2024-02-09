package [[SERVICE_INFO.PACKAGE_NAME]]._global.eventBase;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import [[SERVICE_INFO.PACKAGE_NAME]]._global.infra.AbstractEvent;

import [[SERVICE_INFO.PACKAGE_NAME]].domain.[[TEMPLATE.NAME]];

@Data
@ToString
@EqualsAndHashCode(callSuper=false)
public class [[TEMPLATE.NAME]]Event extends AbstractEvent {
[[TEMPLATE.ATTRIBUTES]]

    public [[TEMPLATE.NAME]]Event([[TEMPLATE.NAME]] aggregate) {
        super(aggregate);
    }

    public [[TEMPLATE.NAME]]Event() {
        super();
    }
}
