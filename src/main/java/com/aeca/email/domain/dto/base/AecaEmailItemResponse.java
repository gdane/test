package com.aeca.email.domain.dto.base;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Schema(description = "Стандартизированный объект ответа сервиса, содержащий один единственный элемент")
public class AecaEmailItemResponse<T> extends AecaEmailAbstractResponse {

    /**
     * Полезная нагрузка
     */
    @Schema(description = "Полезная нагрузка")
    private T data;

    public AecaEmailItemResponse(T data) {
        this.data = data;
    }

    /**
     * @param data полезная нагрузка
     * @param <T>  тип данных
     * @return стандартизированный объект ответа с указанной полезной нагрузкой и кодом ответа {@code 200}
     */
    public static <T> AecaEmailItemResponse<T> ok(T data) {
        AecaEmailItemResponse<T> response = new AecaEmailItemResponse<>(data);
        response.setStatus(200);
        return response;
    }

    public static <T> AecaEmailItemResponse<T> created(T data) {
        AecaEmailItemResponse<T> response = new AecaEmailItemResponse<>(data);
        response.setStatus(201);
        return response;
    }
}
