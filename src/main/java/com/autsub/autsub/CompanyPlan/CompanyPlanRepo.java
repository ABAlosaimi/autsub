package com.autsub.autsub.CompanyPlan;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyPlanRepo extends JpaRepository<CompanyPlan, Long> {
    Optional<CompanyPlan> findByCompany_name(String company_name);
}
