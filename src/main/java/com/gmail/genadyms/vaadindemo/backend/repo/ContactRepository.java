package com.gmail.genadyms.vaadindemo.backend.repo;

import com.gmail.genadyms.vaadindemo.backend.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {
}
