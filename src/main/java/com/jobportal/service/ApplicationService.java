package com.jobportal.service;

import com.jobportal.dto.ApplicationDTO;
import com.jobportal.entity.Application;
import com.jobportal.entity.Job;
import com.jobportal.entity.User;
import com.jobportal.mapper.ApplicationMapper;
import com.jobportal.repository.ApplicationRepository;
import com.jobportal.repository.JobRepository;
import com.jobportal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationService {
    @Autowired private ApplicationRepository appRepo;
    @Autowired private UserRepository userRepo;
    @Autowired private JobRepository jobRepo;

    public void apply(ApplicationDTO dto) {
        User applicant = userRepo.findById(dto.getApplicantId()).orElseThrow();
        Job job = jobRepo.findById(dto.getJobId()).orElseThrow();
        Application application = ApplicationMapper.toEntity(dto, applicant, job);
        appRepo.save(application);
    }

    public List<Application> trackApplications(Long applicantId) {
        return appRepo.findByApplicantId(applicantId);
    }
}