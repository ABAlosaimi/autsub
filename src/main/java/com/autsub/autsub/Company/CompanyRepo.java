package com.autsub.autsub.Company;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepo extends JpaRepository<Company, String> {
    Optional<Company> findByName(String name);
}
