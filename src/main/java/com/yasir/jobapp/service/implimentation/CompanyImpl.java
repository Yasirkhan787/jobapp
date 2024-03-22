package com.yasir.jobapp.service.implimentation;

import com.yasir.jobapp.entities.Company;
import com.yasir.jobapp.repository.CompanyRepository;
import com.yasir.jobapp.service.CompanyService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyImpl implements CompanyService {

    CompanyRepository companyRepository;

    public CompanyImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    //To create company
    @Override
    public void createCompany(Company company) {
        companyRepository.save(company);
    }

    //
    @Override
    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    // Find company by id
    @Override
    public Company findCompanyById(Long id) {
        return companyRepository.findById(id).orElse(null);
    }

    //
    @Override
    public Boolean updateCompanyById(Long id, Company updatedCompany) {
        Optional<Company> companyOptional = companyRepository.findById(id);
        if (companyOptional.isPresent())
        {
            Company comp= companyOptional.get(); // If the Object(job) present get the Object(job) and assign to job variable
            comp.setName(updatedCompany.getName());
            comp.setDescription(updatedCompany.getDescription());
            comp.setJobs(updatedCompany.getJobs());
            companyRepository.save(comp);
            return true;
        }
        return false;
    }

    //
    @Override
    public Boolean deleteCompanyById(@PathVariable Long id) {
        try {
            companyRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
