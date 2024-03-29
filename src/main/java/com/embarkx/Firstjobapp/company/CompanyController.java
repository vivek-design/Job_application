package com.embarkx.Firstjobapp.company;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {
   private  CompanyService companyService;

    public CompanyController(CompanyService companyService) {

        this.companyService = companyService;
    }
    @GetMapping
    public ResponseEntity<List<Company>>getAllCompanies(){

        return new ResponseEntity<>(companyService.getAllCompany(), HttpStatus.OK);
    }


    @PutMapping("/{id}")
    public ResponseEntity<String>updatedCompany(@PathVariable Long id, @RequestBody Company company){
        companyService.updateCompany(company, id);
        return new ResponseEntity<>("Company updated successfully", HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<String>addcompany(@RequestBody Company company){
        companyService.createCompany(company);
        return new ResponseEntity<>("New company added", HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String>deleteCompany(@PathVariable Long id){
        boolean idDelted= companyService.deleteCompany(id);
        if(idDelted){
            return new ResponseEntity<>("Company Deleted successfully", HttpStatus.OK);
        }

        return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompany(@PathVariable Long id){
         Company company=companyService.getCompanyById(id);
         if(company!=null){
             return new ResponseEntity<>(company,HttpStatus.OK);
         }

         return new ResponseEntity<>( HttpStatus.NOT_FOUND);
    }




}
