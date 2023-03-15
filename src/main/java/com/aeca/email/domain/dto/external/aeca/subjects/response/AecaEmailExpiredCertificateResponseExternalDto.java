package com.aeca.email.domain.dto.external.aeca.subjects.response;

import com.aeca.email.domain.AecaEmailNotificationData;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Класс объекта ответа внешнего сервиса, содержащий данные сертификата с истекающим сроком действия
 *
 * @author Aleksandr Rjakhov
 */
@Getter
@Setter
@NoArgsConstructor
public class AecaEmailExpiredCertificateResponseExternalDto implements AecaEmailNotificationData {

    /**
     * Цифровой отпечаток сертификата
     */
    private String fingerprint;

    /**
     * Серийный номер сертификата
     */
    private String serialnumber;

    /**
     * Имя пользователя сертификата
     */
    private String username;

    /**
     * Дата и время окончания срока действия сертификата
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING, timezone = "Europe/Moscow")
    private LocalDateTime expireDate;

    /**
     * Электронный адрес
     */
    private String email;

    @Override
    public String getId() {
        return getFingerprint();
    }
}
