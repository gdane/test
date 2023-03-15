package com.aeca.email.service.events.certificates;

import com.aeca.email.domain.dto.pojo.AecaEmailNotificationLogEvent;

/**
 * @author Aleksandr Rjakhov
 */
public interface AecaEmailNotificationLogEventService {

    /**
     * Фиксация события в журнал отправки уведомления по электронной почте
     *
     * @param event объект события
     */
    void sendEvent(AecaEmailNotificationLogEvent event);

    /**
     * Фиксация события в журнал ошибки отправки уведомления по электронной почте
     *
     * @param errorMessage текст ошибки
     */
    void sendErrorEvent(String errorMessage);

    /**
     * Фиксация события в журнал ошибки отправки уведомления по электронной почте
     *
     * @param event        объект события
     * @param errorMessage текст ошибки
     */
    void sendErrorEvent(AecaEmailNotificationLogEvent event, String errorMessage);
}
