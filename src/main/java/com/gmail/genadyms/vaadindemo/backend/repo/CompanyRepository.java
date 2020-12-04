package com.gmail.genadyms.vaadindemo.backend.repo;

import com.gmail.genadyms.vaadindemo.backend.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
