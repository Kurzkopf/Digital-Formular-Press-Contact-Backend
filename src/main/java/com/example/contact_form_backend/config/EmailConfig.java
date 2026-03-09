package com.example.contact_form_backend.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "app.mail")
public class EmailConfig {
    private String pressRecipient = "testformular@test.com";
    private String adminRecipient = "testadmin@test.com";
    private String sender = "testformular@test.com";
    private boolean pressEnabled = true;
    private boolean adminEnabled = true;
    private boolean senderEnabled = true;

}

