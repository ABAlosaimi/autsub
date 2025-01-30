package com.autsub.autsub.Company;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CompanyRepo extends JpaRepository<Company, Long> {
    Optional<Company> findByName(String name);
    Optional<Company> findByEmail(String email);

    @Modifying
    @Transactional
    @Query(value = "UPDATE company c SET c.email = ?1, c.address = ?2 WHERE c.email = ?1", nativeQuery = true)
    void updateCompanyEmailAndAddress(String email, String address);
}
