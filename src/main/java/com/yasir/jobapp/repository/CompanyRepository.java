package com.yasir.jobapp.repository;

import com.yasir.jobapp.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
