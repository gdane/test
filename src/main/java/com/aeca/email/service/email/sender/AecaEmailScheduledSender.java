package com.aeca.email.service.email.sender;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;


@Slf4j
@Service
@RequiredArgsConstructor
public class AecaEmailScheduledSender {
    private final Set<AecaEmailNotificationSender> senders;

    @Scheduled(cron = "${email-sender.schedule}")
    @Transactional
    public void send() {
        for (AecaEmailNotificationSender sender : senders) {
            try {
                sender.send();
            } catch (Exception e) {
                log.error("Ошибка вызова отправителя сообщений", e);
            }
        }
    }
}
