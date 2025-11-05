package com.jobportal.controller;

import com.jobportal.dto.UserDTO;
import com.jobportal.entity.User;
import com.jobportal.mapper.UserMapper;
import com.jobportal.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDTO userDto) {
        User saved = authService.register(userDto);
        return ResponseEntity.ok(UserMapper.toDto(saved));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDTO loginDto) {
        User user = authService.authenticate(loginDto.getEmail(), loginDto.getPassword());
        if (user == null) return ResponseEntity.status(401).body("Invalid credentials");
        return ResponseEntity.ok(UserMapper.toDto(user));
    }
}
