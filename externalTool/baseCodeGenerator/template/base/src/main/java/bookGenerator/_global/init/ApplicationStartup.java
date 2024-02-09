package bookGenerator._global.init;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;

import bookGenerator.domain.User;
import bookGenerator.domain.UserRepository;

@Component
@RequiredArgsConstructor
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    
    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {
        if(!(event.getApplicationContext().getClass().getName().contains("AnnotationConfigServletWebServerApplicationContext")))
            return;

        this.addAdminAccount();
    }

    private void addAdminAccount() {
        // 프로그램 실행시에 자동으로 Admin 권한 계정을 추가시키기 위해서
        String adminPassword = "admin";
        User savedUser = this.userRepository.save(
            User.builder()
                .email("admin@gmail.com")
                .password(this.passwordEncoder.encode(adminPassword))
                .name("admin")
                .role("Admin")
                .build()
        );

        CustomLogger.debug(
            CustomLoggerType.EFFECT, 
            "Test admin account added", 
            String.format(
                "{email: %s, password: %s, name: %s, role: %s}", 
                savedUser.getEmail(), adminPassword, savedUser.getName(), savedUser.getRole())
        );
    }
}