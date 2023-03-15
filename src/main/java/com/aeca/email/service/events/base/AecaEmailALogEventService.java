package com.aeca.email.service.events.base;

import com.aeca.email.controller.api.feign.logs.AecaLogsFeignApi;
import com.aeca.email.domain.dto.external.aeca.logs.enumeration.dict.AecaLogsActionExternalDictCodes;
import com.aeca.email.domain.dto.external.aeca.logs.pojo.AecaLogsItem;
import com.aeca.email.domain.dto.external.aeca.logs.request.AecaLogsCreateLogRequestExternalDto;
import com.aeca.email.domain.dto.pojo.AecaEmailNotificationLogEvent;

import java.util.List;

import static com.aeca.email.utils.functions.ActionUtils.withTryAction;

/**
 * @author Aleksandr Rjakhov
 */
public abstract class AecaEmailALogEventService implements AecaEmailLogEventService {

    /**
     * Feign-клиент сервиса логирования
     */
    protected abstract AecaLogsFeignApi getLogsFeignApi();

    @Override
    public void send(AecaLogsActionExternalDictCodes action, AecaEmailNotificationLogEvent event, String errorMessage) {
        AecaLogsCreateLogRequestExternalDto requestExternalDto = AecaLogsCreateLogRequestExternalDto.builder()
                .action(action)
                .description(errorMessage)
                .build();

        if (event != null) {
            List<AecaLogsItem<?>> attributes = requestExternalDto.getAttributes();

            if (event.getCommonName() != null) {
                attributes.add(new AecaLogsItem<>(CN_FIELD_NAME, event.getCommonName()));
            }
            attributes.add(new AecaLogsItem<>(EMAIL_FIELD_NAME, event.getEmail()));
            attributes.add(new AecaLogsItem<>(TEMPLATE_FIELD_NAME, event.getTemplate()));
        }

        withTryAction(getLogsFeignApi()::save, requestExternalDto);
    }
}
