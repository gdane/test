package com.aeca.email.domain.entity.base;

import com.aeca.email.domain.AecaEmailFixationDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * Базовый класс для всех сущностей
 * и фиксации даты и времени обновления и создания сущности с типом {@link LocalDateTime}
 */
@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
public abstract class AecaEmailAFixationBaseEntity extends AecaEmailABaseEntity implements AecaEmailFixationDateTime<LocalDateTime> {

    /**
     * Дата создания записи
     */
    @CreationTimestamp
    @Column(name = "created", nullable = false, insertable = false, updatable = false)
    private LocalDateTime created = LocalDateTime.now();

    /**
     * Дата обновления записи
     */
    @UpdateTimestamp
    @Column(name = "updated")
    private LocalDateTime updated;

}
