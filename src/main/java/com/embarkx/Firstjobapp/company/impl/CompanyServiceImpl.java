package com.embarkx.Firstjobapp.company.impl;

import com.embarkx.Firstjobapp.company.Company;
import com.embarkx.Firstjobapp.company.CompanyRepository;
import com.embarkx.Firstjobapp.company.CompanyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {
   private CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Company> getAllCompany() {
       return companyRepository.findAll();
    }

    @Override
    public boolean updateCompany(Company company, Long id) {
        Optional<Company>companyOptional=companyRepository.findById(id);
        if(companyOptional.isPresent()){
            Company company1=companyOptional.get();
            company1.setJobs(company.getJobs());
            company1.setName(company1.getName());
            company1.setDescription(company1.getDescription());
            company1.setName(company1.getName());
            companyRepository.save(company1);
             return true;

        }

        return false;
    }

    @Override
    public void createCompany(Company company) {
        companyRepository.save(company);

    }

    @Override
    public boolean deleteCompany(Long id) {
        if(companyRepository.existsById(id)){
            companyRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Company getCompanyById(Long id) {
        return companyRepository.findById(id).orElse(null);
    }


}
