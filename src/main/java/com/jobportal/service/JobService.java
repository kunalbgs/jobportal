package com.jobportal.service;

import com.jobportal.dto.JobDTO;
import com.jobportal.entity.Job;
import com.jobportal.mapper.JobMapper;
import com.jobportal.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    public Job postJob(JobDTO dto) {
        Job job = JobMapper.toEntity(dto);
        return jobRepository.save(job);
    }

    public List<Job> searchJobs(String keyword) {
        if (keyword == null) keyword = "";
        return jobRepository.findByLocationContainingOrTitleContaining(keyword, keyword);
    }
}