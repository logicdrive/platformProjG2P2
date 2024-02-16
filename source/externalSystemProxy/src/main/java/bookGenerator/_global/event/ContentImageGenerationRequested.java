package bookGenerator._global.event;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import bookGenerator._global.eventBase.ContentEvent;
import bookGenerator._global.eventBase.EventNameAnnotation;

@Data
@NoArgsConstructor
@ToString(callSuper=true)
@EqualsAndHashCode(callSuper=false)
@EventNameAnnotation(eventName="ContentImageGenerationRequested")
public class ContentImageGenerationRequested extends ContentEvent {
    String query;
}