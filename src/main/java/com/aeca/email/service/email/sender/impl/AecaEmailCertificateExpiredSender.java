package com.aeca.email.service.email.sender.impl;

import com.aeca.email.controller.api.feign.subjects.AecaSubCertificateReferenceFeignApi;
import com.aeca.email.domain.dto.base.AecaEmailCollectionResponse;
import com.aeca.email.domain.dto.external.aeca.subjects.request.AecaEmailGetAllExpiredCertificatesRequestExternalDto;
import com.aeca.email.domain.dto.external.aeca.subjects.response.AecaEmailExpiredCertificateResponseExternalDto;
import com.aeca.email.domain.dto.pojo.AecaEmailNotificationLogEvent;
import com.aeca.email.domain.dto.pojo.AecaEmailNotification;
import com.aeca.email.domain.entity.email.AecaEmailDeliveryLogEntity;
import com.aeca.email.domain.entity.email.AecaEmailDeliveryTemplateEntity;
import com.aeca.email.domain.enumeration.dict.AecaEmailDeliveryTypeDictCodes;
import com.aeca.email.properties.api.AecaEmailSenderProperty;
import com.aeca.email.repository.AecaEmailDeliveryLogRepository;
import com.aeca.email.repository.AecaEmailDeliveryTemplateRepository;
import com.aeca.email.service.email.AecaEmailCertificateExpiredService;
import com.aeca.email.service.email.AecaEmailService;
import com.aeca.email.service.email.sender.AecaAbstractEmailNotificationSender;
import com.aeca.email.service.events.certificates.AecaEmailNotificationLogEventService;
import com.aeca.email.utils.response.ResponseUtils;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AecaEmailCertificateExpiredSender extends AecaAbstractEmailNotificationSender<AecaSubCertificateReferenceFeignApi, AecaEmailExpiredCertificateResponseExternalDto> {

    @Getter
    private final AecaSubCertificateReferenceFeignApi feignClient;
    @Getter
    private final AecaEmailDeliveryTemplateRepository deliveryTemplateRepository;
    @Getter
    private final AecaEmailDeliveryLogRepository logRepository;
    @Getter
    private final AecaEmailSenderProperty emailProperties;
    @Getter
    private final AecaEmailService emailService;
    @Getter
    private final AecaEmailNotificationLogEventService emailNotificationLogEventService;

    private final AecaEmailCertificateExpiredService certificateExpiredService;

    @Override
    public void send() {
        List<AecaEmailDeliveryTemplateEntity> deliveryTemplates = getDeliveryTemplateRepository().findByDeliveryTypeCode(AecaEmailDeliveryTypeDictCodes.CERTIFICATE_EXPIRED);

        deliveryTemplates.forEach(deliveryTemplate -> {
            AecaEmailGetAllExpiredCertificatesRequestExternalDto requestExternalDto = new AecaEmailGetAllExpiredCertificatesRequestExternalDto();
            requestExternalDto.setInterval(deliveryTemplate.getInterval());

            AecaEmailCollectionResponse<AecaEmailExpiredCertificateResponseExternalDto> responseBody = ResponseUtils.getBody(getFeignClient().getExpiredCertificates(requestExternalDto));
            List<AecaEmailExpiredCertificateResponseExternalDto> certificates = responseBody.getData().getItems();

            certificates.forEach(certificate -> {
                if (!logRepository.existsByCertificateFingerprintAndDeliveryTemplate(certificate.getFingerprint(), deliveryTemplate)) {
                    send(certificate, deliveryTemplate);
                    sendLogEvent(deliveryTemplate, certificate);
                }
            });
        });
    }

    private void sendLogEvent(AecaEmailDeliveryTemplateEntity deliveryTemplate,
                              AecaEmailExpiredCertificateResponseExternalDto certificate) {
        if (deliveryTemplate == null || certificate == null) {
            return;
        }

        AecaEmailNotificationLogEvent logEvent = new AecaEmailNotificationLogEvent();

        logEvent.setCommonName(certificate.getUsername());
        logEvent.setEmail(certificate.getEmail());
        logEvent.setTemplate(deliveryTemplate.getTemplateName());

        getEmailNotificationLogEventService().sendEvent(logEvent);
    }

    @Override
    protected void logSending(AecaEmailExpiredCertificateResponseExternalDto data, AecaEmailDeliveryTemplateEntity deliveryTemplate) {
        AecaEmailDeliveryLogEntity log = new AecaEmailDeliveryLogEntity();

        log.setCertificateFingerprint(data.getFingerprint());
        log.setDateTime(LocalDateTime.now());
        log.setDeliveryTemplate(deliveryTemplate);

        getLogRepository().save(log);
    }

    @Override
    protected @Nullable AecaEmailNotification mapNotification(@NotNull AecaEmailExpiredCertificateResponseExternalDto data, AecaEmailDeliveryTemplateEntity deliveryTemplate) {
        AecaEmailNotification notification = new AecaEmailNotification();

        notification.setSender(getEmailProperties().getEmail());

        String recipient = data.getEmail();
        if (Objects.isNull(recipient)) {
            return null;
        }
        notification.setRecipient(recipient);

        notification.setSubject(deliveryTemplate.getSubject());
        notification.setBody(certificateExpiredService.create(data));
        notification.setHtml(true);

        return notification;
    }
}
