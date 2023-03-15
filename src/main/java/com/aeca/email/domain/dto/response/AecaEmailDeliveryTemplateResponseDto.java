package com.aeca.email.domain.dto.response;

import com.aeca.email.domain.enumeration.AecaEmailDeliveryTemplateStatusEnum;
import com.aeca.email.domain.enumeration.dict.AecaEmailDeliveryTypeDictCodes;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * Объект ответа сервиса, содержащий данные о шаблоне рассылки
 */
@Getter
@Setter
@NoArgsConstructor
@Schema(description = "Объект ответа сервиса, содержащий данные о шаблоне рассылки")
public class AecaEmailDeliveryTemplateResponseDto implements Serializable {

    /**
     * Идентификатор шаблона рассылки
     */
    @Schema(description = "Идентификатор ресурса")
    private Long id;

    /**
     * Название шаблона
     */
    @Schema(description = "Название шаблона")
    private String templateName;

    /**
     * Тема письма
     */
    @Schema(description = "Тема письма")
    private String subject;

    /**
     * Время до события (мс)
     */
    @Schema(description = "Время до события (мс)")
    private Long interval;

    /**
     * Тип рассылки
     */
    @Schema(description = "Тип рассылки")
    private AecaEmailDeliveryTypeDictCodes deliveryType;

    /**
     * Статус шаблона рассылки
     */
    @Schema(description = "Статус шаблона рассылки")
    private AecaEmailDeliveryTemplateStatusEnum status;

}
