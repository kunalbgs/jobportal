package com.jobportal.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ApplicationDTO {

    private Long id;
    private Long applicantId;
    private Long jobId;

    private String jobTitle;     // ✅ Required for dashboard
    private String status;       // ✅ APPLIED, SHORTLISTED etc.

    private LocalDateTime appliedAt;   // ✅ Should be LocalDateTime
}
