package com.jobportal.entity;

import jakarta.persistence.*;
import lombok.Data;
import com.jobportal.constant.ApplicationStatus;
import java.util.Date;

@Entity
@Table(name = "applications")
@Data
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "applicant_id", nullable = false)
    private User applicant;

    @ManyToOne
    @JoinColumn(name = "job_id", nullable = false)
    private Job job;

    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;

    @Temporal(TemporalType.TIMESTAMP)
    private Date appliedAt;
}
