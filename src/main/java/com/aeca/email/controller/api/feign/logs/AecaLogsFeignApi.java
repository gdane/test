package com.aeca.email.controller.api.feign.logs;


import com.aeca.email.configuration.modules.AecaEmailEjbcaFeignModule;
import com.aeca.email.domain.dto.external.aeca.logs.request.AecaLogsCreateLogRequestExternalDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "AecaLogsFeignApi",
        url = "${aeca-api.aeca-logs}/api/v2/log",
        configuration = AecaEmailEjbcaFeignModule.class)
public interface AecaLogsFeignApi {

    /**
     * Сохранение события в журнал
     *
     * @param request запрос фиксации события в журнале
     */
    @PostMapping("/save")
    void save(AecaLogsCreateLogRequestExternalDto request);
}
