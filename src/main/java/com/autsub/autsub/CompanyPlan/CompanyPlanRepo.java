package com.autsub.autsub.CompanyPlan;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface CompanyPlanRepo extends JpaRepository<CompanyPlan, Long> {
    Optional<CompanyPlan> findByCompanyName(String name);
     
    @Modifying
    @Transactional
    @Query(value = "UPDATE company_plan SET title = ?1, category = ?2, description = ?3, recurring = ?4, price = ?5, trial = ?6 WHERE id = ?7", nativeQuery = true)
    void updateCompanyPlan(String title, String category, String description, String recurring, float price, boolean trial, Long planID);

    @Modifying
    @Transactional
    @Query(value = "UPDATE company_plan SET last_offer_price = ?2, last_offer_date = NOW() WHERE id = ?1", nativeQuery = true)
    void updateCompanyPlanLastOffer(Long planId, float offerPrice);

    @Modifying
    @Transactional
    @Query(value = "UPDATE company_plan SET subscriptions = subscriptions + 1 WHERE id = ?1", nativeQuery = true)
    void updateCompanyPlanSubscription(Long planId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE company_plan SET cancelation = cancelation + 1 WHERE id = ?1", nativeQuery = true)
    void updateCompanyPlanCancelation(Long planId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE company_plan  SET stumbled_subscription = stumbled_subscription + 1, stumble_reason = ?2WHERE id = ?1", nativeQuery = true)
    void updateCompanyPlanStumbledSubscription(Long planId, String stumbleReasone);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO company_plan (title, category, description, recurring, price, trial, company_name) VALUES (?1, ?2, ?3, ?4, ?5, ?6)", nativeQuery = true)
    void insertCompanyPlan(String title, String category, String description, String recurring, float price, boolean trial);

    Optional<List<CompanyPlan>> findAllByCompanyName(String companyName);

}
