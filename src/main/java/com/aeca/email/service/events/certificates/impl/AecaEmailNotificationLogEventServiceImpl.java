package com.aeca.email.service.events.certificates.impl;

import com.aeca.email.controller.api.feign.logs.AecaLogsFeignApi;
import com.aeca.email.domain.dto.external.aeca.logs.enumeration.dict.AecaLogsActionExternalDictCodes;
import com.aeca.email.domain.dto.pojo.AecaEmailNotificationLogEvent;
import com.aeca.email.service.events.base.AecaEmailALogEventService;
import com.aeca.email.service.events.certificates.AecaEmailNotificationLogEventService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Aleksandr Rjakhov
 */
@Service
@RequiredArgsConstructor
public class AecaEmailNotificationLogEventServiceImpl extends AecaEmailALogEventService implements AecaEmailNotificationLogEventService {

    @Getter
    private final AecaLogsFeignApi logsFeignApi;

    @Override
    public void sendEvent(AecaEmailNotificationLogEvent event) {
        send(AecaLogsActionExternalDictCodes.SEND_EMAIL_NOTE, event, null);
    }

    @Override
    public void sendErrorEvent(String errorMessage) {
        send(AecaLogsActionExternalDictCodes.ERROR_SEND_EMAIL_NOTE, null, errorMessage);
    }

    @Override
    public void sendErrorEvent(AecaEmailNotificationLogEvent event, String errorMessage) {
        send(AecaLogsActionExternalDictCodes.ERROR_SEND_EMAIL_NOTE, event, errorMessage);
    }
}
