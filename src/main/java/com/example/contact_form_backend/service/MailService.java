package com.example.contact_form_backend.service;

import com.example.contact_form_backend.entity.ContactSubmission;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender mailSender;

    //Diese E-Mail wird beim Abschicken des Fomulars verschickt.
    public void sendPressDepartmentMail(ContactSubmission s) {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setFrom("testformular@test.com");
        mail.setTo("testautomatisch@test.com");
        mail.setSubject("Neuer Pressekontakt für das " + s.getMuseum());
        mail.setText(buildText(s));
        mailSender.send(mail);
    }

    //Diese E-Mail wird nur vom Admin angefordert.
    public void sendAdminMail(ContactSubmission s) {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setFrom("testformular@test.com");
        mail.setTo("testadmin@test.com");
        mail.setSubject("Neuer Pressekontakt für das " + s.getMuseum()
                + "(E-Mail auf Admin Anfrage gesendet)");
        mail.setText(buildText(s));
        mailSender.send(mail);
    }

    /*
    public void sendUserConfirmation(ContactSubmission s) {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(s.getEmail());
        mail.setSubject("Wir haben deine Anfrage erhalten");
        mail.setText("Danke! Deine Anfrage ist eingegangen am " + s.getCreatedAt());
        mailSender.send(mail);
    }*/

    private String buildText(ContactSubmission s) {
        return "ID: " + s.getId() + "\n"
                + "Datum: " + s.getSubmissionDate() + "\n"
                + "Name: " + s.getName() + "\n"
                + "E-Mail: " + s.getEmail() + "\n"
                + "Museum: " + s.getMuseum() + "\n"
                + "Adresse: " + s.getAddress() + "\n"
                + "Arbeitgeber: " + s.getEmployer() + "\n"
                + "Nachricht: " + (s.getMessage() == null ? "" : s.getMessage()) + "\n";
        // signature würde ich i.d.R. NICHT als Base64 in die Mail packen (sehr groß)
    }
}

