package com.jobportal.repository;

import com.jobportal.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface JobRepository extends JpaRepository<Job, Long> {

    // ✅ Search method EXACT same name
    List<Job> findByLocationContainingIgnoreCaseOrTitleContainingIgnoreCase(
            String location,
            String title
    );

    // ✅ Dashboard recent jobs
    List<Job> findTop5ByOrderByCreatedAtDesc();
}
