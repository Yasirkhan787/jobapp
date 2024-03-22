package com.yasir.jobapp.service;

import com.yasir.jobapp.entities.Company;

import java.util.List;

public interface CompanyService {

    void createCompany(Company company);
    List<Company> findAll();
    Company findCompanyById(Long companyId);

    Boolean updateCompanyById(Long id, Company company);
    Boolean deleteCompanyById(Long id);
}