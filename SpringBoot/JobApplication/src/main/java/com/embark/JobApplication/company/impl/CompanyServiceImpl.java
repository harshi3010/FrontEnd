package com.embark.JobApplication.company.impl;

import com.embark.JobApplication.company.Company;
import com.embark.JobApplication.company.CompanyRepository;
import com.embark.JobApplication.company.CompanyService;
import com.embark.JobApplication.job.Job;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {

    //get list of all companies from database
    private CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public boolean updateCompany(Company company, Long id) {
        Optional<Company> companyOptional = companyRepository.findById(id);
        if (companyOptional.isPresent()) {      //means objects is present
            Company companyToUpdate = companyOptional.get();
            companyToUpdate.setName(company.getName());
            companyToUpdate.setDescription(company.getDescription());
            companyToUpdate.setJobs(company.getJobs());
            companyRepository.save(companyToUpdate);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void createCompany(Company company) {
        companyRepository.save(company);
    }

    @Override
    public boolean deleteCompanyById(Long id) {
        if(companyRepository.existsById(id)){
            companyRepository.deleteById(id);
            return true;
        }else{
            return false;
        }

    }

    @Override
    public Company getCompanyById(Long id) {

        /*
        for(Company comp:company){  //traversing through all the companies object and see if any id matches..
            if(comp.getId() == id){
                return comp;
            }
        }
        return null;
         */
        return companyRepository.findById(id).orElse(null);
    }
}