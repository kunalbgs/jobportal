package com.jobportal.dto;

import lombok.Data;
import java.util.Date;

@Data
public class ApplicationDTO {
    private Long id;
    private Long applicantId;
    private Long jobId;
    private String status;
    private Date appliedAt;
}
