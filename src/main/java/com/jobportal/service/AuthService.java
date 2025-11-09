package com.jobportal.service;

import com.jobportal.constant.Role;
import com.jobportal.dto.UserDTO;
import com.jobportal.entity.User;
import com.jobportal.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;

    // simple in-memory OTP store (replace with DB/redis for prod)
    private final ConcurrentHashMap<String,String> otpStore = new ConcurrentHashMap<>();

    public boolean register(UserDTO dto) {
        if (userRepository.findByUsername(dto.getUsername()).isPresent()) return false;
        if (userRepository.findByEmail(dto.getEmail()).isPresent()) return false;

        User u = new User();
        u.setUsername(dto.getUsername());
        u.setEmail(dto.getEmail());
        u.setPassword(passwordEncoder.encode(dto.getPassword()));
        u.setRole(dto.getRole() != null
                ? Role.valueOf(dto.getRole().trim().toUpperCase())
                : Role.APPLICANT);
        u.setProvider("local");
        userRepository.save(u);
        return true;
    }

    public boolean login(String username, String rawPassword) {
        Optional<User> opt = userRepository.findByUsername(username);
        if (opt.isEmpty()) return false;
        return passwordEncoder.matches(rawPassword, opt.get().getPassword());
    }

    public boolean emailExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    public String sendOtp(String email) {
        String otp = String.valueOf(100000 + (int)(Math.random()*900000));
        otpStore.put(email, otp);
        emailService.send(email, "Password Reset OTP", "Your OTP: " + otp);
        return otp;
    }

    public boolean verifyOtp(String email, String otp) {
        return otp.equals(otpStore.get(email));
    }

    public boolean resetPassword(String email, String newPassword) {
        User u = userRepository.findByEmail(email).orElse(null);
        if (u == null) return false;
        u.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(u);
        otpStore.remove(email);
        return true;
    }
}
