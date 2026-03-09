package com.example.contact_form_backend.controller;

import com.example.contact_form_backend.config.EmailConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/config")
@RequiredArgsConstructor
@Slf4j
public class AdminConfigController {

    private final EmailConfig config;

    @PostMapping("/mail")
    public ResponseEntity<String> updateMailConfig(
            @RequestParam(required = false) String pressRecipient,
            @RequestParam(required = false) String adminRecipient,
            @RequestParam(required = false) String sender,
            @RequestParam(defaultValue = "true") boolean pressEnabled,
            @RequestParam(defaultValue = "true") boolean adminEnabled,
            @RequestParam(defaultValue = "true") boolean senderEnabled)
    {

        if (pressRecipient != null) {
            config.setPressRecipient(pressRecipient);
            log.info("Press-Recipient geändert: {}", pressRecipient);
        }
        if (adminRecipient != null) {
            config.setAdminRecipient(adminRecipient);
            log.info("Admin-Recipient geändert: {}", adminRecipient);
        }
        if (sender != null) {
            config.setSender(sender);
            log.info("Sender geändert: {}", sender);
        }
        config.setPressEnabled(pressEnabled);
        config.setAdminEnabled(adminEnabled);
        config.setSenderEnabled(senderEnabled);

        return ResponseEntity.ok("Mail-Konfig aktualisiert!\n" +
                "Press: " + config.getPressRecipient() + " (enabled: " + pressEnabled + ")\n" +
                "Admin: " + config.getAdminRecipient() + " (enabled: " + adminEnabled + ")\n" +
                "Sender: " + config.getSender() + " (enabled: " + senderEnabled + ")");
    }

    @GetMapping("/mail")
    public ResponseEntity<EmailConfig> getMailConfig() {
        return ResponseEntity.ok(config);
    }
}
