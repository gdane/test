package com.aeca.email.domain.dto.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@Schema(description = "Стандартизированный объект ответа сервиса, содержащий информацию об ошибке")
public class AecaEmailErrorResponse extends AecaEmailAbstractResponse {
    /**
     * Код ошибки {@code xxxyyy}, где {@code xxx} - код сервиса, {@code yyy} - внутренний код ошибки
     */
    @Schema(description = "Код ошибки xxxyyy, где xxx - код сервиса, yyy - внутренний код ошибки")
    private int code;

    /**
     * Описание ошибки
     */
    @Schema(description = "Описание ошибки")
    private String message = "";

    /**
     * Детали ошибки
     */
    @Schema(description = "Детали ошибки")
    private Data data;

    @Getter
    @Setter
    @NoArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Schema(description = "Объект с деталями ошибки")
    public static class Data {

        /**
         * Идентификатор сущности, работа с которым привела к ошибке
         */
        @Schema(description = "Идентификатор сущности, работа с которым привела к ошибке")
        private Object id;

        /**
         * Поля, не прошедшие валидацию
         */
        @Schema(description = "Поля, не прошедшие валидацию")
        private Map<String, Object> fields = new HashMap<>();

        /**
         * Сообщение, чем вызвано исключение
         */
        @Schema(description = "Сообщение, чем вызвано исключение")
        private String causedBy;
    }
}
