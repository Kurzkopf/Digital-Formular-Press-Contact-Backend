package com.example.contact_form_backend.dto;

import lombok.Data;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ContactSubmissionResponse {
    private Long id;
    private String message;
    private LocalDateTime submittedAt;
}
