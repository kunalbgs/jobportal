package com.jobportal.mapper;

import com.jobportal.dto.ApplicationDTO;
import com.jobportal.entity.Application;
import com.jobportal.entity.Job;
import com.jobportal.entity.User;
import com.jobportal.constant.ApplicationStatus;
import java.util.Date;

public class ApplicationMapper {

    public static Application toEntity(ApplicationDTO dto, User applicant, Job job) {
        if (dto == null) return null;

        Application application = new Application();
        if (dto.getId() != null)
            application.setId(dto.getId());

        application.setApplicant(applicant);
        application.setJob(job);

        // Status safe set
        if (dto.getStatus() != null) {
            try {
                application.setStatus(ApplicationStatus.valueOf(dto.getStatus().toUpperCase()));
            } catch (Exception e) {
                application.setStatus(ApplicationStatus.PENDING);
            }
        } else {
            application.setStatus(ApplicationStatus.PENDING);
        }

        application.setAppliedAt(dto.getAppliedAt() != null ? dto.getAppliedAt() : new Date());
        return application;
    }

    public static ApplicationDTO toDto(Application application) {
        if (application == null) return null;

        ApplicationDTO dto = new ApplicationDTO();
        dto.setId(application.getId());
        dto.setApplicantId(application.getApplicant() != null ? application.getApplicant().getId() : null);
        dto.setJobId(application.getJob() != null ? application.getJob().getId() : null);
        dto.setStatus(application.getStatus() != null ? application.getStatus().name() : null);
        dto.setAppliedAt(application.getAppliedAt());
        return dto;
    }
}
