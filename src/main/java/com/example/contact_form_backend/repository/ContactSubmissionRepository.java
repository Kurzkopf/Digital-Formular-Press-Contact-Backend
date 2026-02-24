package com.example.contact_form_backend.repository;

import com.example.contact_form_backend.entity.ContactSubmission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ContactSubmissionRepository extends JpaRepository<ContactSubmission, Long> {

    // Optional: Zusätzliche Query-Methoden
    List<ContactSubmission> findByEmail(String email);
    List<ContactSubmission> findByCreatedAtBetween(LocalDateTime start, LocalDateTime end);
}
