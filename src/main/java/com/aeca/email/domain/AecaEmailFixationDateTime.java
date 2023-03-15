package com.aeca.email.domain;

/**
 * Интерфейс фиксации даты и времени сущности
 *
 * @param <DT> тип даты и времени создания/обновления сущности
 */
public interface AecaEmailFixationDateTime<DT> {

    DT getCreated();

    void setCreated(DT dateTime);

    DT getUpdated();

    void setUpdated(DT dateTime);
}
