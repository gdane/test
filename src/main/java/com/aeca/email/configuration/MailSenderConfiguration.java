package com.aeca.email.configuration;

import com.aeca.email.properties.api.AecaEmailSenderProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailSenderConfiguration {

    @Bean
    public JavaMailSender mailSender(AecaEmailSenderProperty appProperties) {
        JavaMailSenderImpl sender = new JavaMailSenderImpl();

        sender.setHost(appProperties.getHost());
        sender.setPort(appProperties.getPort());

        sender.setUsername(appProperties.getLogin());
        sender.setPassword(appProperties.getPassword());

        Properties senderProperties = sender.getJavaMailProperties();
        senderProperties.put("mail.transport.protocol", appProperties.getProtocol());
        senderProperties.put("mail.smtp.auth", appProperties.isSmtpAuth());
        senderProperties.put("mail.smtp.starttls.enable", appProperties.isSmtpStartTlsEnable());

        return sender;
    }
}
