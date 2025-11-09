package com.jobportal.service;

import com.jobportal.dto.ApplicationDTO;
import com.jobportal.entity.Application;
import com.jobportal.entity.Job;
import com.jobportal.entity.User;
import com.jobportal.mapper.ApplicationMapper;
import com.jobportal.repository.ApplicationRepository;
import com.jobportal.repository.JobRepository;
import com.jobportal.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationService {

    private final ApplicationRepository appRepo;
    private final UserRepository userRepo;
    private final JobRepository jobRepo;

    public ApplicationService(ApplicationRepository appRepo,
                              UserRepository userRepo,
                              JobRepository jobRepo) {
        this.appRepo = appRepo;
        this.userRepo = userRepo;
        this.jobRepo = jobRepo;
    }

    // ✅ APPLY JOB (Save Application)
    public void apply(ApplicationDTO dto) {
        User applicant = userRepo.findById(dto.getApplicantId())
                .orElseThrow(() -> new RuntimeException("Applicant not found"));

        Job job = jobRepo.findById(dto.getJobId())
                .orElseThrow(() -> new RuntimeException("Job not found"));

        Application application = ApplicationMapper.toEntity(dto, applicant, job);

        appRepo.save(application);
    }

    // ✅ TRACK APPLICATIONS BY USER
    public List<Application> trackApplications(Long applicantId) {
        return appRepo.findByApplicant_Id(applicantId);
    }

}
