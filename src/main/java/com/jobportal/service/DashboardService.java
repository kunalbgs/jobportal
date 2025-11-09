package com.jobportal.service;

import com.jobportal.entity.Application;
import com.jobportal.entity.Job;
import com.jobportal.repository.ApplicationRepository;
import com.jobportal.repository.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final JobRepository jobRepository;
    private final ApplicationRepository applicationRepository;

    // ✅ Stats
    public Map<String, Object> getStats() {
        Map<String, Object> stats = new HashMap<>();

        stats.put("totalJobs", jobRepository.count());
        stats.put("totalApplications", applicationRepository.count());
        stats.put("shortlisted", 0);  // placeholder
        stats.put("interviews", 0);   // placeholder

        return stats;
    }

    // ✅ Recent jobs
    public List<Job> getRecentJobs() {
        return jobRepository.findTop5ByOrderByCreatedAtDesc();
    }

    // ✅ Recent applications
    public List<Application> getRecentApplications() {
        return applicationRepository.findTop5ByOrderByAppliedAtDesc();
    }
}
