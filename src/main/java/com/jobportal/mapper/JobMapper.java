package com.jobportal.mapper;

import com.jobportal.dto.JobDTO;
import com.jobportal.entity.Job;

public class JobMapper {

    public static Job toEntity(JobDTO dto) {
        Job job = new Job();
        job.setTitle(dto.getTitle());
        job.setCompany(dto.getCompany());
        job.setLocation(dto.getLocation());
        job.setDescription(dto.getDescription());
        return job;
    }

    public static JobDTO toDTO(Job job) {
        JobDTO dto = new JobDTO();
        dto.setId(job.getId());
        dto.setTitle(job.getTitle());
        dto.setCompany(job.getCompany());
        dto.setLocation(job.getLocation());
        dto.setDescription(job.getDescription());
        return dto;
    }
}
