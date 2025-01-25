package com.autsub.autsub.CompanyPlan;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CompanyPlanRepo extends JpaRepository<CompanyPlan, Long> {
    Optional<CompanyPlan> findByCompany_name(String company_name);
     
    @Modifying
    @Transactional
    @Query(value = "UPDATE company_plan cp SET cp.title = ?1, cp.category = ?2, cp.info = ?3, cp.recurring = ?4, cp.price = ?5, cp.trial = ?6 WHERE cp.title = ?1 AND cp.category = ?2 AND cp.info = ?3 AND cp.recurring = ?4 AND cp.price = ?5 AND cp.trial = ?6", nativeQuery = true)
    void updateCompanyPlan(String title, String category, String info, String recurring, String price, String trial);

    @Modifying
    @Transactional
    @Query(value = "UPDATE company_plan cp SET cp.last_offer_price = cp.last_offer_price + 1, cp.last_offer_date = NOW() WHERE cp.id = ?1", nativeQuery = true)
    void updateCompanyPlanLastOffer(Long id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE company_plan cp SET cp.subscription = cp.subscription + 1 WHERE cp.id = ?1", nativeQuery = true)
    void updateCompanyPlanSubscription(Long planId);


    @Modifying
    @Transactional
    @Query(value = "UPDATE company_plan cp SET cp.cancelation = cp.cancelation + 1 WHERE cp.id = ?1", nativeQuery = true)
    void updateCompanyPlanCancelation(Long planId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE company_plan cp SET cp.stumbled_subscription = cp.stumbled_subscription + 1, cp.stumble_reason = ?2WHERE cp.id = ?1", nativeQuery = true)
    void updateCompanyPlanStumbledSubscription(Long planId, String stumbleReasone);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO company_plan (title, category, info, recurring, price, trial, company_name) VALUES (?1, ?2, ?3, ?4, ?5, ?6, ?7)", nativeQuery = true)
    void insertCompanyPlan(String title, String category, String info, String recurring, int price, boolean trial);

}
