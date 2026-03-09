package com.example.contact_form_backend.service;

import com.example.contact_form_backend.dto.ContactSubmissionRequest;
import com.example.contact_form_backend.dto.ContactSubmissionResponse;
import com.example.contact_form_backend.entity.ContactSubmission;
import com.example.contact_form_backend.repository.ContactSubmissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactSubmissionService {

    private final ContactSubmissionRepository repository;
    private final MailService mailService;

    @Transactional
    public ContactSubmissionResponse submitForm(ContactSubmissionRequest request) {
        ContactSubmission submission = new ContactSubmission();
        submission.setName(request.getName());
        submission.setEmail(request.getEmail());
        submission.setAddress(request.getAddress());
        submission.setMuseum(request.getMuseum());
        submission.setEmployer(request.getEmployer());
        submission.setMessage(request.getMessage());
        submission.setSubmissionDate(request.getDate());
        submission.setSignature(request.getSignature());
        submission.setPicture(request.getPicture());

        ContactSubmission saved = repository.save(submission);

        mailService.sendPressDepartmentMail(saved);

        return new ContactSubmissionResponse(
                saved.getId(),
                "Formular erfolgreich übermittelt",
                saved.getCreatedAt()
        );
    }

    public List<ContactSubmission> getAllSubmissions() {
        return repository.findAll();
    }

    public ContactSubmission getSubmissionById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Submission nicht gefunden"));
    }
}
