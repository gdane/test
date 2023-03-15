package com.aeca.email.domain.entity.email;

import com.aeca.email.domain.entity.base.AecaEmailAFixationBaseEntity;
import com.aeca.email.domain.entity.dict.AecaEmailDeliveryTypeDictEntity;
import com.aeca.email.domain.enumeration.AecaEmailDeliveryTemplateStatusEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * Класс сущности шаблонов рассылки
 */
@Entity
@Table(name = "aeca_email_delivery_template")
@Getter
@Setter
@NoArgsConstructor
public class AecaEmailDeliveryTemplateEntity extends AecaEmailAFixationBaseEntity {

    /**
     * Тип рассылки
     */
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "delivery_type_id", referencedColumnName = "id", nullable = false)
    private AecaEmailDeliveryTypeDictEntity deliveryType;

    /**
     * Статус
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private AecaEmailDeliveryTemplateStatusEnum status = AecaEmailDeliveryTemplateStatusEnum.ACTIVE;

    /**
     * Название шаблона
     */
    @Column(name = "template_name", nullable = false)
    private String templateName;

    /**
     * Интервал времения, перед событием
     */
    @Column(name = "interval", nullable = false)
    private Long interval;

    /**
     * Тема письма
     */
    @Column(name = "subject", nullable = false)
    private String subject;

    /**
     * Журнал рассылки
     */
    @OneToMany(mappedBy = "deliveryTemplate",
            fetch = FetchType.LAZY,
            orphanRemoval = true,
            cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.REMOVE})
    private List<AecaEmailDeliveryLogEntity> logs;

}
