package com.aeca.email.domain.dto.base;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Стандартизированный объект ответа сервиса
 */
@Getter
@Setter
@NoArgsConstructor
@Schema(description = "Стандартизированный объект ответа сервиса")
@JsonPropertyOrder({"status"})
public abstract class AecaEmailAbstractResponse {

    /**
     * HTTP-статус ответа (должен совпадать с возвращаемым статусом)
     */
    private int status;
}
