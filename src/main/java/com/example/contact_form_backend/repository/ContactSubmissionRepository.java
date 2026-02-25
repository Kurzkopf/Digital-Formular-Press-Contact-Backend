package com.example.contact_form_backend.repository;

import com.example.contact_form_backend.entity.ContactSubmission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ContactSubmissionRepository extends JpaRepository<ContactSubmission, Long> {

}
