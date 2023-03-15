package com.aeca.email.service.email.sender.impl;

import com.aeca.email.domain.dto.pojo.AecaEmailNotification;
import com.aeca.email.properties.api.AecaEmailSenderProperty;
import com.aeca.email.service.email.AecaEmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@RequiredArgsConstructor
@Slf4j
public class AecaEmailServiceImpl implements AecaEmailService {

    private final AecaEmailSenderProperty properties;
    private final JavaMailSender mailSender;

    @Override
    public void send(@NotNull AecaEmailNotification object) throws MailException, MessagingException {


        if (!properties.isSending()) {
            log.info("Режим отправки сообщения на email выключен, сообщение не будет доставлено.");
            return;
        }

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, "UTF-8");

        helper.setFrom(object.getSender());
        helper.setTo(object.getRecipient());
        helper.setSubject(object.getSubject());
        helper.setText(object.getBody(), object.isHtml());

        mailSender.send(message);
        log.info("Сообщение отправлено");
    }
}
