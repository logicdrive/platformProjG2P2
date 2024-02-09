package [[SERVICE_INFO.PACKAGE_NAME]];

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.ApplicationContext;

import [[SERVICE_INFO.PACKAGE_NAME]]._global.config.kafka.KafkaProcessor;

@SpringBootApplication
@EnableBinding(KafkaProcessor.class)
public class BootApplication {

    public static ApplicationContext applicationContext;

    public static void main(String[] args) {
        applicationContext = SpringApplication.run(BootApplication.class, args);
    }
}
