package com.jobportal.controller;

import com.jobportal.dto.JobDTO;
import com.jobportal.entity.Job;
import com.jobportal.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class JobController {

    @Autowired
    private JobService jobService;

    @PostMapping("/post-job")
    public String postJob(@ModelAttribute JobDTO dto) {
        jobService.postJob(dto);
        return "redirect:/dashboard";
    }

    @GetMapping("/search")
    public String search(@RequestParam(required = false) String keyword, Model model) {
        List<Job> jobs = jobService.searchJobs(keyword);
        model.addAttribute("jobs", jobs);
        return "job-list";
    }
}