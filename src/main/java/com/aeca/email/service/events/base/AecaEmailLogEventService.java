package com.aeca.email.service.events.base;

import com.aeca.email.domain.dto.external.aeca.logs.enumeration.dict.AecaLogsActionExternalDictCodes;
import com.aeca.email.domain.dto.pojo.AecaEmailNotificationLogEvent;

/**
 * @author Aleksandr Rjakhov
 */
public interface AecaEmailLogEventService {

    String CN_FIELD_NAME = "CN";
    String EMAIL_FIELD_NAME = "email";
    String TEMPLATE_FIELD_NAME = "шаблон";

    /**
     * Фиксация события в журнал отправки данных по электронной почте
     *
     * @param action       код события
     * @param event        объект события
     * @param errorMessage текст ошибки
     */
    void send(AecaLogsActionExternalDictCodes action, AecaEmailNotificationLogEvent event, String errorMessage);
}
