package com.jobportal.mapper;

import com.jobportal.dto.ApplicationDTO;
import com.jobportal.entity.Application;
import com.jobportal.entity.Job;
import com.jobportal.entity.User;

public class ApplicationMapper {

    public static Application toEntity(ApplicationDTO dto, User applicant, Job job) {

        Application app = new Application();
        app.setApplicant(applicant);
        app.setJob(job);

        // appliedAt auto-set होगा, manually set मत करना

        return app;
    }

    public static ApplicationDTO toDTO(Application app) {

        ApplicationDTO dto = new ApplicationDTO();

        dto.setId(app.getId());
        dto.setApplicantId(app.getApplicant().getId());
        dto.setJobId(app.getJob().getId());

        dto.setJobTitle(app.getJob().getTitle());     // ✅ FIXED
        dto.setStatus("APPLIED");                     // ✅ Default dashboard display
        dto.setAppliedAt(app.getAppliedAt());         // ✅ LocalDateTime -> LocalDateTime

        return dto;
    }
}
