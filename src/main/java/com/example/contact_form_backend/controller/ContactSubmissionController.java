package com.example.contact_form_backend.controller;

import com.example.contact_form_backend.dto.ContactSubmissionRequest;
import com.example.contact_form_backend.dto.ContactSubmissionResponse;
import com.example.contact_form_backend.entity.ContactSubmission;
import com.example.contact_form_backend.service.ContactSubmissionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contact")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:3000"})
public class ContactSubmissionController {

    private final ContactSubmissionService service;

    @PostMapping("/submit")
    public ResponseEntity<ContactSubmissionResponse> submitForm(
            @Valid @RequestBody ContactSubmissionRequest request) {
        ContactSubmissionResponse response = service.submitForm(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/submissions")
    public ResponseEntity<List<ContactSubmission>> getAllSubmissions() {
        return ResponseEntity.ok(service.getAllSubmissions());
    }

    @GetMapping("/submissions/{id}")
    public ResponseEntity<ContactSubmission> getSubmissionById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getSubmissionById(id));
    }
}
