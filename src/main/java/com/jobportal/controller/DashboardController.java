//package com.jobportal.controller;
//
//import com.jobportal.service.DashboardService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.oauth2.core.user.OAuth2User;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//
//@Controller
//@RequiredArgsConstructor
//public class DashboardController {
//    private final DashboardService dashboardService;
//
//    @GetMapping("/dashboard")
//    public String dashboard(Model model, Authentication auth) {
//        model.addAttribute("stats", dashboardService.getStats());
//        model.addAttribute("recentJobs", dashboardService.getRecentJobs());
//        model.addAttribute("recentApplications", dashboardService.getRecentApplications());
//
//        String name = auth != null ? auth.getName() : "Guest";
//        if (auth != null && auth.getPrincipal() instanceof OAuth2User o) {
//            if (o.getAttribute("name") != null) name = o.getAttribute("name");
//            model.addAttribute("email", o.getAttribute("email"));
//            model.addAttribute("picture", o.getAttribute("picture"));
//        }
//        model.addAttribute("displayName", name);
//        return "dashboard/index";
//    }
//}
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
        model.addAttribute("stats", dashboardService.getStats());
        model.addAttribute("recentJobs", dashboardService.getRecentJobs());
        model.addAttribute("recentApplications", dashboardService.getRecentApplications());

        String name = auth != null ? auth.getName() : "Guest";
        if (auth != null && auth.getPrincipal() instanceof OAuth2User o) {
            if (o.getAttribute("name") != null) name = o.getAttribute("name");
            model.addAttribute("email", o.getAttribute("email"));
            model.addAttribute("picture", o.getAttribute("picture"));
        }
        model.addAttribute("displayName", name);
        return "dashboard/index";
    }
}
