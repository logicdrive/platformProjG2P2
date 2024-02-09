package [[SERVICE_INFO.PACKAGE_NAME]]._global.init;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> { 
    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {
    }
}