package com.jobportal.controller;

import com.jobportal.dto.UserDTO;
import com.jobportal.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @GetMapping("/login")
    public String loginPage(Model model) {
        return "auth/login";
    }

    @PostMapping("/login-process")
    public String loginProcess(@RequestParam String username,
                               @RequestParam String password,
                               Model model) {
        if (!authService.login(username, password)) {
            model.addAttribute("error", "Invalid username or password");
            return "auth/login";
        }
        return "redirect:/dashboard";
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("user", new UserDTO());
        return "auth/register";
    }

    @PostMapping("/register")
    public String registerSubmit(@ModelAttribute("user") UserDTO user, Model model) {
        if (!authService.register(user)) {
            model.addAttribute("error", "User already exists!");
            return "auth/register";
        }
        return "redirect:/auth/login?registered";
    }

    // Forgot + OTP
    @GetMapping("/forgot-password")
    public String forgotPage() {
        return "auth/forgot-password";
    }

    @PostMapping("/forgot-password/send-otp")
    public String sendOtp(@RequestParam String email, Model model) {
        if (!authService.emailExists(email)) {
            model.addAttribute("error", "Email not found!");
            return "auth/forgot-password";
        }
        authService.sendOtp(email);
        model.addAttribute("email", email);
        model.addAttribute("stage", "otp");
        return "auth/forgot-password";
    }

    @PostMapping("/forgot-password/verify-otp")
    public String verifyOtp(@RequestParam String email,
                            @RequestParam String otp, Model model) {
        if (!authService.verifyOtp(email, otp)) {
            model.addAttribute("email", email);
            model.addAttribute("stage", "otp");
            model.addAttribute("error", "Invalid OTP!");
            return "auth/forgot-password";
        }
        model.addAttribute("email", email);
        model.addAttribute("stage", "reset");
        return "auth/forgot-password";
    }

    @PostMapping("/forgot-password/reset")
    public String reset(@RequestParam String email,
                        @RequestParam String password,
                        Model model) {
        if (!authService.resetPassword(email, password)) {
            model.addAttribute("email", email);
            model.addAttribute("stage", "reset");
            model.addAttribute("error", "Something went wrong!");
            return "auth/forgot-password";
        }
        return "redirect:/auth/login?reset";
    }
}
