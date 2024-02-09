package bookGenerator._global.eventBase;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface EventNameAnnotation {
    String eventName() default "";
}