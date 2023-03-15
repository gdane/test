package com.aeca.email.domain.dto.external.aeca.subjects.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс запроса на получение данных сертификатов с истекающим сроком действия с дополнительным списком цифровых отпечатков сертификатов, по которым сделана рассылка
 *
 * @author Aleksandr Rjakhov
 */
@Getter
@Setter
@NoArgsConstructor
@Schema(description = "Запрос на получение данных сертификатов с истекающим сроком действия с дополнительным списком цифровых отпечатков сертификатов, по которым сделана рассылка")
public class AecaEmailGetAllExpiredCertificatesRequestExternalDto {

    /**
     * Целочисленное представление даты и времени интервала срока действия сертификата
     */
    @NotNull(message = "Целочисленное представление даты и времени не должно быть пустым")
    @Min(value = 0, message = "Целочисленное представление даты и времени не должно быть отрицательным")
    @Schema(description = "Целочисленное представление даты и времени интервала срока действия сертификата", minimum = "0")
    private Long interval;

    /**
     * Список цифровых отпечатков сертификатов, по которым сделана рассылка
     */
    @Schema(description = "Список цифровых отпечатков сертификатов, по которым сделана рассылка")
    private List<String> excludedFingerprints = new ArrayList<>();

}
