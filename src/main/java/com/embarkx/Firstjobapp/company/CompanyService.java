package com.embarkx.Firstjobapp.company;

import java.util.List;

public interface CompanyService {

    List<Company>getAllCompany();
    boolean updateCompany(Company company, Long id);
    void createCompany(Company company);

    boolean deleteCompany(Long id);

    Company getCompanyById(Long id);
}
