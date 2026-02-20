package me.satyaki.TicketingApi.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Security configuration for the Ticketing API.
 * Configures which endpoints require authentication and which are public.
 * 
 * @author Satyaki
 * @version 1.0
 * @since 1.0
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * Configures the security filter chain for HTTP requests.
     * Allows public access to user creation endpoint while securing other
     * endpoints.
     * 
     * @param http the HttpSecurity to configure
     * @return configured SecurityFilterChain
     * @throws Exception if configuration fails
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // Allow public access to create user endpoint (for registration)
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers(HttpMethod.POST, "/api/users", "/api/users/").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/users").permitAll()
                        // Secure all other endpoints - require authentication
                        .anyRequest().authenticated())
                // For now, disable CSRF for testing purposes
                // In production, configure CSRF properly or use JWT tokens
                .csrf(csrf -> csrf.disable())
                // Use HTTP Basic authentication for testing
                .httpBasic(basic -> {});

        return http.build();
    }
}
