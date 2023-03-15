package com.aeca.email.utils.slice;

import lombok.experimental.UtilityClass;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;

@UtilityClass
public class SliceCollectionUtils {

    /**
     * Выполняет преобразование пагинированного объекта
     *
     * @param page пагинированный объект
     * @param <T>        тип данных внутри коллекции
     * @return объект-обертка, хранящий смещение, ограничение размера и обработанную коллекцию объектов
     */
    public <T> SliceResult<T> slice(Page<T> page) {
        SliceResult<T> result = new SliceResult<>();

        Pageable pageable = page.getPageable();
        result.setLimit(pageable.getPageSize());
        result.setOffset(pageable.getOffset());
        result.setPageNumber(pageable.getPageNumber());

        result.setCount(page.getTotalElements());
        result.setTotalPages(page.getTotalPages());
        result.setPage(page.toList());

        return result;
    }

    /**
     * Выполняет преобразование коллекции
     *
     * @param collection объект коллекции
     * @param <T>        тип данных внутри коллекции
     * @return объект-обертка, хранящий смещение, ограничение размера и обработанную коллекцию объектов
     */
    public <T> SliceResult<T> slice(Collection<T> collection) {
        SliceResult<T> result = new SliceResult<>();

        result.setLimit(collection.size());
        result.setOffset(0);
        result.setPageNumber(0);

        result.setCount(collection.size());
        result.setTotalPages(1);
        result.setPage(collection);

        return result;
    }

}
