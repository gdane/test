package com.aeca.email.configuration.annotation;


import com.aeca.email.domain.pojo.AecaEmailCollectionRequest;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Parameters({
        @Parameter(
                description = "Пагинация: ограничение на размер списка",
                name = AecaEmailCollectionRequest.FieldNames.pageLimit,
                schema = @Schema(type = "int", defaultValue = "2147483647", minimum = "0"),
                in = ParameterIn.QUERY
        ),
        @Parameter(
                description = "Пагинация: смещение от начала списка",
                name = AecaEmailCollectionRequest.FieldNames.pageOffset,
                schema = @Schema(type = "int", defaultValue = "0", minimum = "0"),
                in = ParameterIn.QUERY
        ),
        @Parameter(
                description = "Сортировка: имя поля, по которому выполнять сортировку",
                name = AecaEmailCollectionRequest.FieldNames.sortBy,
                array = @ArraySchema(schema = @Schema(type = "string", defaultValue = "id")),
                in = ParameterIn.QUERY
        ),
        @Parameter(
                description = "Сортировка: направление сортировки",
                name = AecaEmailCollectionRequest.FieldNames.sortDirection,
                schema = @Schema(type = "string", defaultValue = "asc", allowableValues = {"asc", "desc"}),
                in = ParameterIn.QUERY
        ),
})
@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiCollectionRequest {
}
