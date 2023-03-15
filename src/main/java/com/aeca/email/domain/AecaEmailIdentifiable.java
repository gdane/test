package com.aeca.email.domain;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;
import java.util.Comparator;

/**
 * Базовый интерфейс сущности
 *
 * @param <ID> тип идентификатора сущности
 * @implNote Идентификатор должен реализовывать интерфейсы:
 * <ul>
 *  <li/> {@link Comparable Comparable&lt;TId&gt;} - для дальнейшей возможной сортировки
 *  <li/> {@link Serializable} - для сохранения в базу данных
 * </ul>
 */
@JsonPropertyOrder("id")
public interface AecaEmailIdentifiable<ID extends Comparable<ID> & Serializable> extends Serializable {

    static <ID extends Comparable<ID> & Serializable, T extends AecaEmailIdentifiable<ID>> Comparator<T> comparator() {
        return Comparator.comparing(AecaEmailIdentifiable::getId);
    }

    ID getId();

    void setId(ID id);
}
