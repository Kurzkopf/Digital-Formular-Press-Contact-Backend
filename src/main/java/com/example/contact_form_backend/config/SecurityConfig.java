package com.example.contact_form_backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // CORS KOMPLETT DEAKTIVIEREN
                .cors(cors -> cors.disable())

                // CSRF DEAKTIVIEREN (für POST-Requests wichtig!)
                .csrf(csrf -> csrf.disable())

                // ALLE API-Endpoints öffentlich machen
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/**").permitAll()  // Deine API
                        .anyRequest().authenticated()            // Rest braucht Login
                )

                // Session-Management (optional)
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );

        return http.build();
    }
}

