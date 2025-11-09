package com.jobportal.config;

import com.jobportal.security.CustomOAuth2UserService;
import com.jobportal.security.OAuth2LoginSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private final OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler;
    private final CustomOAuth2UserService customOAuth2UserService;

    public SecurityConfig(OAuth2LoginSuccessHandler successHandler,
                          CustomOAuth2UserService customUserService) {
        this.oAuth2LoginSuccessHandler = successHandler;
        this.customOAuth2UserService = customUserService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/auth/**",
                                "/css/**",
                                "/js/**",
                                "/images/**",
                                "/oauth2/**",          // ✅ allow auth start
                                "/login/oauth2/**"     // ✅ allow provider callback
                        ).permitAll()
                        .anyRequest().authenticated()
                )
                .oauth2Login(oauth -> oauth
                        .loginPage("/auth/login")
                        .userInfoEndpoint(u -> u.userService(customOAuth2UserService))
                        .successHandler(oAuth2LoginSuccessHandler)
                )
                .formLogin(f -> f
                        .loginPage("/auth/login")
                        .loginProcessingUrl("/auth/login-process")
                        .defaultSuccessUrl("/dashboard", true)
                        .permitAll()
                )
                .logout(log -> log
                        .logoutUrl("/auth/logout")
                        .logoutSuccessUrl("/auth/login?logout")
                        .permitAll()
                );

        return http.build();
    }
}
