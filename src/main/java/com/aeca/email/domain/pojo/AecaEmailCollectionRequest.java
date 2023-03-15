package com.aeca.email.domain.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

@Getter
@Setter
@NoArgsConstructor
@Schema(description = "Объект запроса коллекции")
@FieldNameConstants(innerTypeName = "FieldNames")
public class AecaEmailCollectionRequest {
    @Schema(description = "Пагинация: смещение от начала списка")
    private Integer pageOffset = 0;
    @Schema(description = "Пагинация: ограничение на размер списка")
    private Integer pageLimit = Integer.MAX_VALUE;
    @Schema(description = "Сортировка: направление сортировки")
    private String sortDirection;
    @Schema(description = "Сортировка: имя полей, по которомым выполнять сортировку")
    private String[] sortBy;

}
