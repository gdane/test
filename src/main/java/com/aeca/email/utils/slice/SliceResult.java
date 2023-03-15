package com.aeca.email.utils.slice;

import com.aeca.email.domain.dto.base.AecaEmailCollectionResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class SliceResult<T> {
    /**
     * Количество элементов в коллекции
     */
    private long count;

    /**
     * Ограничение размера коллекции
     */
    private int limit;

    /**
     * Смещение от начала коллекции
     */
    private long offset;

    /**
     * Общее число страниц
     */
    private int totalPages;

    /**
     * Номер страницы выборки
     */
    private int pageNumber;

    /**
     * Преобразованная коллекция
     */
    private Collection<T> page;

    /**
     * Преобразует всю текущую коллекцию объектов
     *
     * @param collectionMapperFunc функция преобразования коллекции
     * @param <U>                  новый тип данных
     * @return новый объект с теми же параметрами, но преобразованной коллекцией
     */
    public <U> SliceResult<U> mapData(Function<Collection<T>, Collection<U>> collectionMapperFunc) {
        SliceResult<U> result = new SliceResult<>();

        result.count = count;
        result.limit = limit;
        result.offset = offset;
        result.pageNumber = pageNumber;
        result.totalPages = totalPages;

        result.page = collectionMapperFunc.apply(page);
        return result;
    }

    /**
     * Преобразует каждый элемент коллекции
     *
     * @param mapperFunc функция преобразования каждого отдельного объекта коллекции
     * @param <U>        новый тип данных
     * @return новый объект с теми же параметрами, но преобразованной коллекцией
     */
    public <U> SliceResult<U> mapDataElements(Function<T, U> mapperFunc) {
        return mapData(collection -> collection.parallelStream().map(mapperFunc).collect(Collectors.toList()));
    }

    /**
     * Выполняет преобразование текущего объекта в {@link AecaEmailCollectionResponse}:
     * <pre>
     * {
     *     status: 200,
     *     data: {
     *         range: {
     *             count: {@link #getCount()},
     *             limit: {@link #getLimit()},
     *             offset: {@link #getOffset()}
     *         },
     *     items: {@link #getPage()}
     *     }
     * }
     * </pre>
     *
     * @return объект из описания
     */
    public AecaEmailCollectionResponse<T> toResponseBody() {
        AecaEmailCollectionResponse<T> body = new AecaEmailCollectionResponse<>();
        body.setStatus(200);
        AecaEmailCollectionResponse.Data<T> data = body.getData();
        AecaEmailCollectionResponse.Data.Range range = data.getRange();

        data.getItems().addAll(this.page);
        range.setLimit(limit);
        range.setCount(count);
        range.setOffset(offset);
        range.setPageNumber(pageNumber);
        range.setTotalPages(totalPages);

        return body;
    }
}
