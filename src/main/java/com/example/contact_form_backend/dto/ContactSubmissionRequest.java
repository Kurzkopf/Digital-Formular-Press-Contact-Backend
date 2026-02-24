package com.example.contact_form_backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ContactSubmissionRequest {

    @NotBlank(message = "Name ist erforderlich")
    private String name;

    @NotBlank(message = "E-Mail ist erforderlich")
    @Email(message = "Ungültige E-Mail-Adresse")
    private String email;

    @NotBlank(message = "Postanschrift ist erforderlich")
    private String address;

    @NotBlank(message = "Museum ist erforderlich")
    private String museum;

    @NotBlank(message = "Arbeitgeber ist erforderlich")
    private String employer;

    private String message;

    @NotNull(message = "Datum ist erforderlich")
    private LocalDate date;

    @NotBlank(message = "Unterschrift ist erforderlich")
    private String signature;
}
