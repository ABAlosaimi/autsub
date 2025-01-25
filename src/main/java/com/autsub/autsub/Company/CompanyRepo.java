package com.autsub.autsub.Company;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepo extends JpaRepository<Company, Long> {
    Optional<Company> findByName(String name);

    @Modifying
    @Query("UPDATE company c SET c.email = ?1, c.address = ?2 WHERE c.email = ?1 AND c.address = ?2")
    void updateCompanyEmailAndAddress(String email, String address);
}
