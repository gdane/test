package com.aeca.email.domain.entity.base;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * Базовый класс для всех словарей,
 * где идентификатором выступает поле {@link Long} {@code id},
 * а значением {@link String String} {@code value}
 */
@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
public abstract class AecaEmailABaseDictionaryEntity extends AecaEmailABaseEntity {

    /**
     * Значение элемента словаря
     */
    @Column(name = "value", nullable = false, unique = true)
    private String value;

}
