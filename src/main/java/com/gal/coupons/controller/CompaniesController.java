package com.gal.coupons.controller;

import com.gal.coupons.dto.CompanyDTO;
import com.gal.coupons.entities.Company;
import com.gal.coupons.exceptions.ServerException;
import com.gal.coupons.logic.CompaniesLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompaniesController {

    private CompaniesLogic companiesLogic;

    @Autowired
    public CompaniesController(CompaniesLogic companiesLogic) {
        this.companiesLogic = companiesLogic;
    }

    //http://localhost:8080/companies
    @PostMapping
    public void createCompany(@RequestBody Company company) throws ServerException{
        this.companiesLogic.createCompany(company);
    }

    //http://localhost:8080/companies/id
    @GetMapping("{companyId}")
    public CompanyDTO getCompanyById(@PathVariable("companyId") int companyId) throws ServerException{
        return this.companiesLogic.getCompanyById(companyId);
    }

    //http://localhost:8080/companies/byName?companyName=?
    @GetMapping("/byName")
    public CompanyDTO getCompanyByName(@RequestParam("companyName") String companyName) throws ServerException{
        return this.companiesLogic.getCompanyByName(companyName);
    }

    //http://localhost:8080/companies
    @GetMapping
    public List<CompanyDTO> getAllCompanies() throws ServerException{
        return companiesLogic.getAllCompanies();
    }

    //http://localhost:8080/companies/
    @PutMapping
    public void updateCompany(@RequestBody Company company) throws ServerException{
        companiesLogic.updateCompany(company);
    }

    //http://localhost:8080/companies/id
    @DeleteMapping("{companyId}")
    public void removeCompany(@PathVariable("companyId") int companyId) throws ServerException {
        companiesLogic.removeCompany(companyId);
    }

}
