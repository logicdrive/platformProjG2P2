package bookGenerator._global.event;


import lombok.NoArgsConstructor;

import bookGenerator._global.eventBase.ContentEvent;
import bookGenerator._global.eventBase.EventNameAnnotation;

@NoArgsConstructor
@EventNameAnnotation(eventName="ContentImageDeleteRequsted")
public class ContentImageDeleteRequsted extends ContentEvent {
}
