package com.jobportal.service;

import com.jobportal.dto.JobDTO;
import com.jobportal.entity.Job;
import com.jobportal.mapper.JobMapper;
import com.jobportal.repository.JobRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {

    private final JobRepository jobRepo;

    public JobService(JobRepository jobRepo) {
        this.jobRepo = jobRepo;
    }

    // ✅ CREATE JOB (POST JOB)
    public Job postJob(JobDTO dto) {
        Job job = JobMapper.toEntity(dto);
        return jobRepo.save(job);
    }

    // ✅ SEARCH JOBS
    public List<Job> searchJobs(String keyword) {
        return jobRepo.findByLocationContainingIgnoreCaseOrTitleContainingIgnoreCase(
                keyword, keyword
        );
    }

    // ✅ RECENT JOBS (Dashboard)
    public List<Job> getRecentJobs() {
        return jobRepo.findTop5ByOrderByCreatedAtDesc();
    }

    // ✅ GET ALL JOBS
    public List<Job> getAllJobs() {
        return jobRepo.findAll();
    }
}
