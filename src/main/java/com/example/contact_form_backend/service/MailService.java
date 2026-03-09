package com.example.contact_form_backend.service;

import com.example.contact_form_backend.config.EmailConfig;
import com.example.contact_form_backend.entity.ContactSubmission;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender mailSender;
    private final EmailConfig config;

    //Diese E-Mail wird beim Abschicken des Fomulars verschickt.
    public void sendPressDepartmentMail(ContactSubmission s) {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setFrom(config.getSender());
        mail.setTo(config.getPressRecipient());
        mail.setSubject("Neuer Pressekontakt für das " + s.getMuseum());
        mail.setText(buildText(s));
        mailSender.send(mail);
    }

    //Diese E-Mail wird nur vom Admin angefordert.
    public void sendAdminMail(ContactSubmission s) {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setFrom(config.getSender());
        mail.setTo(config.getAdminRecipient());
        mail.setSubject("Neuer Pressekontakt für das " + s.getMuseum()
                + "(E-Mail auf Admin Anfrage gesendet)");
        mail.setText(buildText(s));
        mailSender.send(mail);
    }

    private String buildText(ContactSubmission s) {
        return "ID: " + s.getId() + "\n"
                + "Datum: " + s.getSubmissionDate() + "\n"
                + "Name: " + s.getName() + "\n"
                + "E-Mail: " + s.getEmail() + "\n"
                + "Museum: " + s.getMuseum() + "\n"
                + "Adresse: " + s.getAddress() + "\n"
                + "Arbeitgeber: " + s.getEmployer() + "\n"
                + "Nachricht: " + (s.getMessage() == null ? "" : s.getMessage()) + "\n";
    }
}

