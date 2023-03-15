package com.aeca.email.service.email;


import com.aeca.email.domain.dto.pojo.AecaEmailNotification;

import javax.mail.MessagingException;

public interface AecaEmailService {
    void send(AecaEmailNotification object) throws MessagingException;
}
