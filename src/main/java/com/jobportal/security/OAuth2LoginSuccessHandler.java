package com.jobportal.security;

import com.jobportal.constant.Role;
import com.jobportal.entity.User;
import com.jobportal.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Component
public class OAuth2LoginSuccessHandler implements AuthenticationSuccessHandler {

    private final UserRepository userRepository;

    public OAuth2LoginSuccessHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {

        try {
            OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
            Map<String, Object> attr = oAuth2User.getAttributes();

            // Google attributes
            String email   = attr != null ? (String) attr.get("email")   : null;
            String name    = attr != null ? (String) attr.get("name")    : null;
            String picture = attr != null ? (String) attr.get("picture") : null;

            // If email missing, generate a placeholder (avoid crashes)
            if (email == null || email.isBlank()) {
                // last resort: use sub as unique key if available
                String sub = attr != null ? String.valueOf(attr.get("sub")) : null;
                email = (sub != null ? sub : UUID.randomUUID().toString()) + "@google.local";
            }

            User user = userRepository.findByEmail(email).orElse(null);

            if (user == null) {
                user = new User();
                user.setEmail(email);
                user.setUsername(email); // simple default; change if needed
                user.setName(name);
                user.setPicture(picture);
                user.setProvider("google");
                user.setRole(Role.APPLICANT);
            } else {
                // update profile fields if changed
                user.setName(name);
                user.setPicture(picture);
                user.setProvider("google");
            }

            userRepository.save(user);

            // Always go to dashboard on success
            response.sendRedirect("/dashboard");
        } catch (Exception ex) {
            // If anything unexpected happens, send to login with a readable flag
            response.sendRedirect("/auth/login?error=oauth2");
        }
    }
}
