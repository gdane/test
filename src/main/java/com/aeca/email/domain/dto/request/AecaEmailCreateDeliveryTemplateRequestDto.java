package com.aeca.email.domain.dto.request;

import com.aeca.email.domain.enumeration.dict.AecaEmailDeliveryTypeDictCodes;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Класс запроса на создание нового шаблона рассылки
 */
@Getter
@Setter
@NoArgsConstructor
@Schema(description = "Запрос на создание нового шаблона рассылки")
public class AecaEmailCreateDeliveryTemplateRequestDto implements Serializable {

    /**
     * Название шаблона
     */
    @NotBlank(message = "Название шаблона отсутствует")
    @Schema(description = "Название шаблона",
            example = "Title",
            required = true)
    private String templateName;

    /**
     * Тема письма
     */
    @NotBlank(message = "Тема письма отсутствует")
    @Schema(description = "Тема письма",
            example = "Subject",
            required = true)
    private String subject;

    /**
     * Время до события (мс)
     */
    @NotBlank(message = "Время до события отсутствует")
    @Schema(description = "Время до события (мс)",
            example = "1000",
            required = true)
    private Long interval;

    /**
     * Тип рассылки
     */
    @NotNull(message = "Тип рассылки отсутствует")
    @Schema(description = "Тип рассылки",
            enumAsRef = true,
            example = "CERTIFICATE_EXPIRED",
            required = true)
    private AecaEmailDeliveryTypeDictCodes deliveryType;

}
