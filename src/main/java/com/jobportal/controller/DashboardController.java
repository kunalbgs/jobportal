package com.jobportal.controller;

import com.jobportal.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping("/dashboard")
    public String dashboard(Model model, Authentication auth) {

        // ✅ Dashboard Stats
        model.addAttribute("stats", dashboardService.getStats());
        model.addAttribute("recentJobs", dashboardService.getRecentJobs());
        model.addAttribute("recentApplications", dashboardService.getRecentApplications());

        // ✅ Default values
        String name = "Guest";
        String email = "";
        String picture = "";

        // ✅ User Login check
        if (auth != null) {

            // ✅ OAUTH USER (Google / LinkedIn)
            if (auth.getPrincipal() instanceof OAuth2User o) {

                if (o.getAttribute("name") != null)
                    name = o.getAttribute("name");

                if (o.getAttribute("email") != null)
                    email = o.getAttribute("email");

                if (o.getAttribute("picture") != null)
                    picture = o.getAttribute("picture");
            }
        }

        // ✅ pass data to HTML
        model.addAttribute("displayName", name);
        model.addAttribute("email", email);
        model.addAttribute("picture", picture);

        return "dashboard/index";
    }
}
