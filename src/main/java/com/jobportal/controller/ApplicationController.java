package com.jobportal.controller;

import com.jobportal.dto.ApplicationDTO;
import com.jobportal.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ApplicationController {
    @Autowired private ApplicationService appService;

    @PostMapping("/apply")
    public String apply(@ModelAttribute ApplicationDTO dto) {
        appService.apply(dto);
        return "application-success";
    }

    @GetMapping("/track")
    public String track(@RequestParam Long applicantId, Model model) {
        model.addAttribute("applications", appService.trackApplications(applicantId));
        return "application-list";
    }
}