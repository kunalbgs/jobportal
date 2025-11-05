//package com.jobportal.controller;
//
//import com.jobportal.dto.UserDTO;
//import com.jobportal.entity.User;
//import com.jobportal.mapper.UserMapper;
//import com.jobportal.service.AuthService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/auth")
//public class AuthController {
//
//    @Autowired
//    private AuthService authService;
//
//    @PostMapping("/register")
//    public ResponseEntity<?> register(@RequestBody UserDTO userDto) {
//        User saved = authService.register(userDto);
//        return ResponseEntity.ok(UserMapper.toDto(saved));
//    }
//
//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody UserDTO loginDto) {
//        User user = authService.authenticate(loginDto.getEmail(), loginDto.getPassword());
//        if (user == null) return ResponseEntity.status(401).body("Invalid credentials");
//        return ResponseEntity.ok(UserMapper.toDto(user));
//    }
//}

package com.jobportal.controller;

import com.jobportal.dto.UserDTO;
import com.jobportal.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    // === GET: Login Page ===
    @GetMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute("error", null); // clear previous error if any
        return "auth/login";
    }

    // === POST: Login Form Submit ===
    @PostMapping("/login")
    public String loginSubmit(@RequestParam String username,
                              @RequestParam String password,
                              @RequestParam String role,
                              Model model) {
        boolean success = authService.login(username, password);
        if (success) {
            return "redirect:/dashboard";
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "auth/login";
        }
    }

    // === GET: Register Page ===
    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("user", new UserDTO());
        return "auth/register";
    }

    // === POST: Register Form Submit ===
    @PostMapping("/register")
    public String registerSubmit(@ModelAttribute("user") UserDTO user, Model model) {
        boolean success = authService.register(user);
        if (success) {
            return "redirect:/auth/login";
        } else {
            model.addAttribute("error", "Registration failed. Username or email may already exist.");
            return "auth/register";
        }
    }
}