package com.aeca.email.controller.api.feign.subjects;

import com.aeca.email.configuration.modules.AecaEmailEjbcaFeignModule;
import com.aeca.email.controller.api.feign.AecaFeignApi;
import com.aeca.email.domain.dto.base.AecaEmailCollectionResponse;
import com.aeca.email.domain.dto.external.aeca.subjects.request.AecaEmailGetAllExpiredCertificatesRequestExternalDto;
import com.aeca.email.domain.dto.external.aeca.subjects.response.AecaEmailExpiredCertificateResponseExternalDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "AecaSubCertificateReferenceFeignApi",
        url = "${aeca-api.subjects}/api/v2/certificate-reference",
        configuration = AecaEmailEjbcaFeignModule.class)
public interface AecaSubCertificateReferenceFeignApi extends AecaFeignApi {

    /**
     * Получение списка сертификатов с истекающим сроком действия
     *
     * @param requestDto запрос на получение данных сертификатов с истекающим сроком действия с дополнительным списком цифровых отпечатков сертификатов, по которым сделана рассылка
     * @return список сертификатов с истекающим сроком действия
     */
    @PostMapping("/get-expired-certificates")
    ResponseEntity<AecaEmailCollectionResponse<AecaEmailExpiredCertificateResponseExternalDto>> getExpiredCertificates(
            @RequestBody AecaEmailGetAllExpiredCertificatesRequestExternalDto requestDto);
}
