package com.jobportal.repository;

import com.jobportal.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
 * Repository for Job entity.
 * Supports search by location or title.
 */
public interface JobRepository extends JpaRepository<Job, Long> {
    List<Job> findByLocationContainingOrTitleContaining(String location, String title);
}