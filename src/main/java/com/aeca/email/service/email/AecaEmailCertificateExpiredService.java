package com.aeca.email.service.email;

import com.aeca.email.domain.dto.external.aeca.subjects.response.AecaEmailExpiredCertificateResponseExternalDto;

public interface AecaEmailCertificateExpiredService {

    /**
     * Создание тела сообщения по объекту ответа внешнего сервиса
     *
     * @param data объект ответа внешнего сервиса, содержащий данные сертификата с истекающим сроком действия
     * @return тело сообщения
     */
    String create(AecaEmailExpiredCertificateResponseExternalDto data);
}
