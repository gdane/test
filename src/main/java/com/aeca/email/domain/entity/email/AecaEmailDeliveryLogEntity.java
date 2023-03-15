package com.aeca.email.domain.entity.email;

import com.aeca.email.domain.entity.base.AecaEmailABaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Класс сущности журнал рассылки
 */
@Entity
@Table(name = "aeca_email_delivery_log")
@Getter
@Setter
@NoArgsConstructor
public class AecaEmailDeliveryLogEntity extends AecaEmailABaseEntity {

    /**
     * Дата и время события
     */
    @CreationTimestamp
    @Column(name = "date_time", nullable = false)
    private LocalDateTime dateTime = LocalDateTime.now();

    /**
     * Ссылка на шаблон рассылки
     */
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "delivery_template_id", referencedColumnName = "id", nullable = false)
    private AecaEmailDeliveryTemplateEntity deliveryTemplate;

    /**
     * Ссылка на фингерпринт сертификакта
     */
    @Column(name = "certificate_fingerprint", nullable = false)
    private String certificateFingerprint;

    /**
     * Примечание
     */
    @Column(name = "note")
    private String note;
}
