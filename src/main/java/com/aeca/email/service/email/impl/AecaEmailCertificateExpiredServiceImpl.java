package com.aeca.email.service.email.impl;

import com.aeca.email.domain.dto.external.aeca.subjects.response.AecaEmailExpiredCertificateResponseExternalDto;
import com.aeca.email.service.AecaEmailTemplateService;
import com.aeca.email.service.email.AecaEmailCertificateExpiredService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class AecaEmailCertificateExpiredServiceImpl implements AecaEmailCertificateExpiredService {

    private final AecaEmailTemplateService templateService;

    private final Locale defaultLocale = Locale.forLanguageTag("ru");

    @Override
    public String create(AecaEmailExpiredCertificateResponseExternalDto data) {
        log.info("Создание тела сообщения об истечении срока действия сертифката...");
        Map<String, Object> args = new HashMap<>();
        args.put("username", data.getUsername());
        args.put("fingerprint", data.getFingerprint());
        args.put("serialnumber", data.getSerialnumber());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        args.put("expire_date", data.getExpireDate().format(formatter));

        log.info("Рендеринг тела письма...");
        return templateService.processTemplate("email/certificate_expired", args, defaultLocale);
    }
}
