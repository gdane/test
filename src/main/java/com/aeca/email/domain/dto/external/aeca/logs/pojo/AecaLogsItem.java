package com.aeca.email.domain.dto.external.aeca.logs.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * Класс объекта, содержащий данные об обновлении поля
 *
 * @author Aleksandr Rjakhov
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Объект, содержащий данные об обновлении поля")
public class AecaLogsItem<T> implements Serializable {

    /**
     * Название изменяемого поля
     */
    @Schema(description = "Название изменяемого поля")
    private String fieldName;

    /**
     * Изначальное значение
     */
    @Schema(description = "Изначальное значение")
    private T fromValue;

    /**
     * Новое значение
     */
    @Schema(description = "Новое значение")
    private T toValue;

    public AecaLogsItem(String fieldName, T toValue) {
        this.fieldName = fieldName;
        this.toValue = toValue;
    }
}
