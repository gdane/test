package com.aeca.email.domain.entity.dict;

import com.aeca.email.domain.entity.base.AecaEmailABaseCodedDictionaryEntity;
import com.aeca.email.domain.enumeration.dict.AecaEmailDeliveryTypeDictCodes;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Класс сущности словаря существующих типов рассылок
 */
@Entity
@Table(name = "aeca_email_delivery_type_dict")
@Getter
@Setter
@NoArgsConstructor
public class AecaEmailDeliveryTypeDictEntity extends AecaEmailABaseCodedDictionaryEntity<AecaEmailDeliveryTypeDictCodes> {
}
