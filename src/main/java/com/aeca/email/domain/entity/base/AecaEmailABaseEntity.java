package com.aeca.email.domain.entity.base;

import com.aeca.email.domain.AecaEmailIdentifiable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Базовый класс для всех сущностей
 */
@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
@EqualsAndHashCode
public abstract class AecaEmailABaseEntity implements AecaEmailIdentifiable<Long> {

    /**
     * Первичный ключ
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    protected Long id;

}
