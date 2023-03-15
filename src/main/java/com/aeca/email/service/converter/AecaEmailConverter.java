package com.aeca.email.service.converter;

/**
 * Базовый интерфейс конвертации объектов
 */
public interface AecaEmailConverter<E, D> {

    D convert(E data);
}
