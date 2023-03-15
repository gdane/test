package com.aeca.email.service.email.sender;

import com.aeca.email.controller.api.feign.AecaFeignApi;
import com.aeca.email.domain.AecaEmailNotificationData;
import com.aeca.email.domain.dto.pojo.AecaEmailNotificationLogEvent;
import com.aeca.email.domain.dto.pojo.AecaEmailNotification;
import com.aeca.email.domain.entity.email.AecaEmailDeliveryTemplateEntity;
import com.aeca.email.properties.api.AecaEmailSenderProperty;
import com.aeca.email.repository.AecaEmailDeliveryLogRepository;
import com.aeca.email.repository.AecaEmailDeliveryTemplateRepository;
import com.aeca.email.service.email.AecaEmailService;
import com.aeca.email.service.events.certificates.AecaEmailNotificationLogEventService;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Slf4j
public abstract class AecaAbstractEmailNotificationSender<R extends AecaFeignApi, T extends AecaEmailNotificationData> implements AecaEmailNotificationSender {

    protected abstract R getFeignClient();

    protected abstract AecaEmailDeliveryTemplateRepository getDeliveryTemplateRepository();

    protected abstract AecaEmailDeliveryLogRepository getLogRepository();

    protected abstract AecaEmailSenderProperty getEmailProperties();

    protected abstract AecaEmailService getEmailService();

    protected abstract AecaEmailNotificationLogEventService getEmailNotificationLogEventService();

    @Override
    public abstract void send();

    protected void send(@NotNull T data, AecaEmailDeliveryTemplateEntity deliveryTemplate) {
        if (log.isDebugEnabled()) {
            log.info("Попытка отправки сообщения {}(id={})", data.getClass().getSimpleName(), data.getId());
        }

        AecaEmailNotification notification;
        try {
            notification = mapNotification(data, deliveryTemplate);
            if (notification == null) {
                String msg = "Метод преобразования данных в объект сообщения вернул null, сообщение не будет отправлено";
                log.info(msg);
                getEmailNotificationLogEventService().sendErrorEvent(msg);
                return;
            }
        } catch (Exception e) {
            String errorMessage = "Ошибка во время преобразования данных в объект сообщения.";
            log.error(errorMessage, e);
            getEmailNotificationLogEventService().sendErrorEvent(errorMessage);
            return;
        }

        try {
            logSending(data, deliveryTemplate);
            getEmailService().send(notification);
            log.info("Сообщение успешно отправлено");

        } catch (Exception e) {
            String errorMessage = "Ошибка отправки почтового сообщения";
            log.error(errorMessage, e);

            getEmailNotificationLogEventService().sendErrorEvent(
                    new AecaEmailNotificationLogEvent(notification.getRecipient(), deliveryTemplate.getTemplateName()),
                    errorMessage
            );
        }
    }

    /**
     * Логирование отправки сообщения
     *
     * @param data данные уведомления
     */
    protected abstract void logSending(T data, AecaEmailDeliveryTemplateEntity deliveryTemplate);

    /**
     * Преобразование данных уведомления в объект сообщения.
     *
     * @param data данные уведомления
     * @return объект сообщения или {@code null}, если сообщение не нужно отправлять
     * @throws Exception любая ошибка, из-за которой невозможно составить сообщение
     */
    @Nullable
    protected abstract AecaEmailNotification mapNotification(@NotNull T data, AecaEmailDeliveryTemplateEntity deliveryTemplate) throws Exception;
}
