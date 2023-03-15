package com.aeca.email.repository.base;

import com.aeca.email.domain.AecaEmailIdentifiable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * Общий интерфейс репозитория всех сущностей
 *
 * @param <T>  Объект, реализующая базовый интерфейс сущности
 * @param <TID> Объект идентификатор на основе базового абстрактного класса сущностей
 */
@NoRepositoryBean
public interface AecaEmailCommonRepository<T extends AecaEmailIdentifiable<TID>, TID extends Comparable<TID> & Serializable> extends AecaEmailBaseRepository<T, TID>,
        JpaRepository<T, TID> {
}
