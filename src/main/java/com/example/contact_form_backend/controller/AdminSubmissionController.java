package com.example.contact_form_backend.controller;

import com.example.contact_form_backend.entity.ContactSubmission;
import com.example.contact_form_backend.repository.ContactSubmissionRepository;
import com.example.contact_form_backend.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:3000"})
public class AdminSubmissionController {

    private final ContactSubmissionRepository repository;
    private final MailService mailService;

    @GetMapping("/submissions")
    public ResponseEntity<Page<ContactSubmission>> getSubmissions(
            @PageableDefault(size = 50, sort = "createdAt", direction = Sort.Direction.DESC)
            Pageable pageable
    ) {
        return ResponseEntity.ok(repository.findAll(pageable));
    }

    @GetMapping("/submissions/{id}")
    public ResponseEntity<ContactSubmission> getSubmissionById(@PathVariable Long id) {
        ContactSubmission submission = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Submission nicht gefunden"));
        return ResponseEntity.ok(submission);
    }

    @PostMapping("/submission/sendMail/{id}")
    public ResponseEntity<Map<String, String>> sendMail(@PathVariable Long id) {
        try {
            ContactSubmission submission = repository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Submission nicht gefunden"));

            mailService.sendAdminMail(submission);

            return ResponseEntity.ok(Map.of("message", "E-Mail erfolgreich gesendet"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Fehler beim Senden der E-Mail"));
        }
    }
}
