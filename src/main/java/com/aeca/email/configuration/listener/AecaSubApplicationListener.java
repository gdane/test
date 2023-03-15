package com.aeca.email.configuration.listener;

import com.aeca.email.controller.api.feign.logs.AecaLogsFeignApi;
import com.aeca.email.domain.dto.external.aeca.logs.enumeration.AecaLogsUserRoleEnum;
import com.aeca.email.domain.dto.external.aeca.logs.enumeration.dict.AecaLogsActionExternalDictCodes;
import com.aeca.email.domain.dto.external.aeca.logs.request.AecaLogsCreateLogRequestExternalDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

/**
 * Слушатель приложения
 *
 * @author Aleksandr Rjakhov
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class AecaSubApplicationListener {

    private final AecaLogsFeignApi aecaLogsFeignApi;

    /**
     * Метод, отслеживающий старт службы и фиксирующий событие в журнал
     */
    @EventListener(ApplicationReadyEvent.class)
    public void onStartup() {
        sendEvent(AecaLogsActionExternalDictCodes.START_SERVICE);
    }

    /**
     * Метод перед уничтожением компонента с фиксацией события в журнал
     */
    @PreDestroy
    public void onShutdown() {
        sendEvent(AecaLogsActionExternalDictCodes.STOP_SERVICE);
    }

    /**
     * Метод отправки события по запросу в журнал
     *
     * @param code код события
     */
    private void sendEvent(AecaLogsActionExternalDictCodes code) {
        try {
            AecaLogsCreateLogRequestExternalDto request = AecaLogsCreateLogRequestExternalDto.builder()
                    .action(code)
                    .role(AecaLogsUserRoleEnum.NONE)
                    .system(true)
                    .build();

            aecaLogsFeignApi.save(request);
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
    }
}
