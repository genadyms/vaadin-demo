package com.gmail.genadyms.vaadindemo.backend.service;

import com.gmail.genadyms.vaadindemo.backend.entity.Company;
import com.gmail.genadyms.vaadindemo.backend.repo.CompanyRepository;

import java.util.List;

public class CompanyService {
    private CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public List<Company> findAll() {
        return companyRepository.findAll();
    }
}
