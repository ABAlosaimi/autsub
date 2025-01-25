package com.autsub.autsub.CompanyPlan;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface CompanyPlanRepo extends JpaRepository<CompanyPlan, Long> {
    Optional<CompanyPlan> findByCompany_name(String company_name);
     
    @Modifying
    @Transactional
    @Query(value = "UPDATE company_plan cp SET cp.title = ?1, cp.category = ?2, cp.info = ?3, cp.recurring = ?4, cp.price = ?5, cp.trial = ?6 WHERE cp.title = ?1 AND cp.category = ?2 AND cp.info = ?3 AND cp.recurring = ?4 AND cp.price = ?5 AND cp.trial = ?6", nativeQuery = true)
    void updateCompanyPlan(String title, String category, String info, String recurring, String price, String trial);
}
