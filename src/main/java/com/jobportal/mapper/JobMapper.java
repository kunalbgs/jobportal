package com.jobportal.mapper;

import com.jobportal.dto.JobDTO;
import com.jobportal.entity.Job;

public class JobMapper {
    public static Job toEntity(JobDTO dto) {
        if (dto == null) return null;
        Job job = new Job();
        job.setId(dto.getId());
        job.setTitle(dto.getTitle());
        job.setDescription(dto.getDescription());
        job.setLocation(dto.getLocation());
        job.setCompany(dto.getCompany());
        return job;
    }

    public static JobDTO toDto(Job job) {
        if (job == null) return null;
        JobDTO dto = new JobDTO();
        dto.setId(job.getId());
        dto.setTitle(job.getTitle());
        dto.setDescription(job.getDescription());
        dto.setLocation(job.getLocation());
        dto.setCompany(job.getCompany());
        return dto;
    }
}
