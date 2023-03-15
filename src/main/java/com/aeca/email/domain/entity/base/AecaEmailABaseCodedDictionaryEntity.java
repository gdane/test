package com.aeca.email.domain.entity.base;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;

/**
 * Сущность с уникальным кодом записи
 *
 * @param <T> тип кода
 */
@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
public class AecaEmailABaseCodedDictionaryEntity<T extends Enum<T>> extends AecaEmailABaseDictionaryEntity {
    @Enumerated(EnumType.STRING)
    @Column(name = "code", unique = true, nullable = false, updatable = false, insertable = false)
    private T code;
}
