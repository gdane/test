package com.aeca.email.domain.dto.external.aeca.logs.enumeration;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Перечисление ролей пользователя
 *
 * @author Aleksandr Rjakhov
 */
@Getter
@RequiredArgsConstructor
@Schema(description = "Перечисление ролей пользователя")
public enum AecaLogsUserRoleEnum {

    /**
     * Администратор
     */
    @Schema(description = "Администратор")
    ADMINISTRATOR("Администратор"),

    /**
     * Оператор
     */
    @Schema(description = "Оператор")
    OPERATOR("Оператор"),

    /**
     * Нет роли
     */
    @Schema(description = "Нет роли")
    NONE("");

    /**
     * Описание
     */
    private final String description;
}
