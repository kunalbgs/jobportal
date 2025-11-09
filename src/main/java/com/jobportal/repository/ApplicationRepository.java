package com.jobportal.repository;

import com.jobportal.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

    // ✅ Correct method for nested mapping (applicant.id)
    List<Application> findByApplicant_Id(Long applicantId);

    List<Application> findTop5ByOrderByAppliedAtDesc();
}
