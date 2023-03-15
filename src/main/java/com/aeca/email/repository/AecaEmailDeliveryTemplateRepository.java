package com.aeca.email.repository;

import com.aeca.email.domain.entity.email.AecaEmailDeliveryTemplateEntity;
import com.aeca.email.domain.enumeration.dict.AecaEmailDeliveryTypeDictCodes;
import com.aeca.email.repository.base.AecaEmailCommonRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Репозиторий шаблонов рассылки
 */
@Repository
public interface AecaEmailDeliveryTemplateRepository extends AecaEmailCommonRepository<AecaEmailDeliveryTemplateEntity, Long> {

    /**
     * Запрос поиска шаблонов рассылки по их типу
     *
     * @param deliveryType тип рассылки
     * @return найденные шаблоны рассылки
     */
    List<AecaEmailDeliveryTemplateEntity> findByDeliveryTypeCode(AecaEmailDeliveryTypeDictCodes deliveryType);
}
