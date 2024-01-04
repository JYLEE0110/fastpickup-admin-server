package org.fktm.fastpickup.config;

import java.util.Arrays;

import org.fktm.fastpickup.security.handler.APILoginFailureHandler;
import org.fktm.fastpickup.security.handler.APILoginSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Configuration
@Log4j2
@RequiredArgsConstructor
@EnableMethodSecurity
public class CustomSecurityConfig {

    private static final String[] WHITE_LIST = {
            "/api/member/**"
    };

    // 패스워드 암호화
    // 팩토리 메소드
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        log.info("===== Security =====");

        // cors 설정
        return 
            http.
                csrf(config -> 
                        config.disable())
                .cors(config -> 
                        config.configurationSource(corsConfigurationSource()))
                .formLogin(config -> {
                        config.loginPage("/api/member/login");
                        config.successHandler(new APILoginSuccessHandler());
                        config.failureHandler(new APILoginFailureHandler());
                })
                .authorizeHttpRequests(config -> 
                        config.requestMatchers(WHITE_LIST).permitAll())
                .getOrBuild();

    }

    // CrossOrigin 사용 안해도 된다.
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOriginPatterns(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("HEAD", "GET", "POST", "PUT", "DELETE"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }

}
