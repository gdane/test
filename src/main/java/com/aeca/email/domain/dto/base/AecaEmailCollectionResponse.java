package com.aeca.email.domain.dto.base;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Стандартизированный объект ответа сервиса,
 * который содержит коллекцию элементов
 *
 * @param <T> тип элемента в коллекции
 */
@Getter
@NoArgsConstructor
@Schema(description = "Стандартизированный объект ответа сервиса, содержащий коллекцию элементов")
public class AecaEmailCollectionResponse<T> extends AecaEmailAbstractResponse {

    /**
     * Полезная нагрузка
     */
    @Schema(description = "Полезная нагрузка")
    private final Data<T> data = new Data<>();

    @Getter
    @NoArgsConstructor
    @Schema(description = "Полезная нагрузка с коллекцией элементов")
    public static class Data<T> {
        /**
         * Данные по выборке
         */
        @Schema(description = "Данные по выборке")
        private final Range range = new Range();

        /**
         * Коллекция элементов
         */
        @Schema(description = "Коллекция элементов")
        private final List<T> items = new ArrayList<>();

        @Getter
        @Setter
        @NoArgsConstructor
        @Schema(description = "Данные по выборке")
        public static class Range {
            /**
             * Всего элементов
             */
            @Schema(description = " Всего элементов")
            private long count;

            /**
             * Количество пропущенных элементов
             */
            @Schema(description = " Количество пропущенных элементов")
            private long offset;

            /**
             * Ограничение количества элементов в выборке
             */
            @Schema(description = " Ограничение количества элементов в выборке")
            private int limit;

            /**
             * Общее число страниц
             */
            @Schema(description = " Общее число страниц")
            private int totalPages;

            /**
             * Номер страницы выборки
             */
            @Schema(description = " Номер страницы выборки")
            private int pageNumber;
        }
    }
}


