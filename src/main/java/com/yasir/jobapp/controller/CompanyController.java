package com.yasir.jobapp.controller;

import com.yasir.jobapp.entities.Company;
import com.yasir.jobapp.service.CompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    // Find all companies
    @GetMapping
    public ResponseEntity<List<Company>> getCompany(){
        return new ResponseEntity<>(companyService.findAll(), HttpStatus.FOUND);
    }

    // Find company by id
    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable Long id) {
        Company comp = companyService.findCompanyById(id);
        if (comp != null) {
            return new ResponseEntity<>(comp, HttpStatus.FOUND);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Create a new company.
    @PostMapping
    public ResponseEntity<String> createCompany(@RequestBody Company company){
        companyService.createCompany(company);
        return new ResponseEntity<>("Company Created Successfully", HttpStatus.CREATED);
    }

    // Update company by id
    @PutMapping("/{id}")
    public ResponseEntity<String> updateCompanyById(@PathVariable Long id,
                                                @RequestBody Company company){
        Boolean update = companyService.updateCompanyById(id, company);
        if(update){
            return new ResponseEntity<>("Company Updated Successfully", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    // Delete a company by id
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompanyById(@PathVariable Long id) {
        Boolean delete = companyService.deleteCompanyById(id);
        if (delete) {
            return ResponseEntity.ok("Company Deleted Successfully");
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }
}
