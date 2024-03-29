package bookGenerator._global.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;

import lombok.RequiredArgsConstructor;

import bookGenerator.BootApplication;


@Service
@RequiredArgsConstructor
public class JwtTokenService {
    public static JwtTokenService getInstance() {
        return BootApplication.applicationContext.getBean(
            JwtTokenService.class
        );
    }
    
    private final AuthenticationManager authenticationManager; // Spring Security를 활용한 인증을 위해서
    private final JwtEncoder jwtEncoder; // JWT의 간편한 생성을 위한 외부라이브러리
    private @Value("${jwt.issuer}") String jwtConfigIssuer;
    private @Value("${jwt.expire-after-seconds}") Long jwtConfigExpireAfterSeconds;

    
    public String tokenValue(String email, String password) {
        try {
            // Spring Security를 활용한 인증을 수행하기 위해서
            // 인증 실패시 자동으로 예외가 발생됨
            Authentication authentication =
                this.authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password));
            
            ServiceUserDetail serviceUserDetail = ((ServiceUserDetail) authentication.getPrincipal());
            JwtEncoderParameters jwtParameters = JwtEncoderParameters.from(
                JwtClaimsSet.builder()
                    .issuer(this.jwtConfigIssuer)
                    .issuedAt(Instant.now())
                    .expiresAt(Instant.now().plusSeconds(this.jwtConfigExpireAfterSeconds))
                    .subject(serviceUserDetail.getId().toString())
                    .claim("email", serviceUserDetail.getEmail())
                    .claim("name", serviceUserDetail.getName())
                    .claim("role", serviceUserDetail.getRole())
                    .build()
            );
            return this.jwtEncoder.encode(jwtParameters).getTokenValue();

        } catch (AuthenticationException ex) {
            throw new AuthenticationFailedException(ex.getMessage());
        }
    }
}
