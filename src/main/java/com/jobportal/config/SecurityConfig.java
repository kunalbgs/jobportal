package com.jobportal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Disable CSRF for simplicity (enable in production with tokens)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/auth/**",     // login, register, logout
                                "/css/**",      // static CSS
                                "/js/**",       // static JS
                                "/images/**"    // optional: static images
                        ).permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/auth/login")              // GET login page
                        .loginProcessingUrl("/auth/login")     // POST login form
                        .defaultSuccessUrl("/dashboard", true) // redirect after login
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/auth/logout")             // logout URL
                        .logoutSuccessUrl("/auth/login?logout")// redirect after logout
                        .permitAll()
                );

        return http.build();
    }
}