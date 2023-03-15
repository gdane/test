package com.aeca.email.repository.dictionary;

import com.aeca.email.domain.entity.dict.AecaEmailDeliveryTypeDictEntity;
import com.aeca.email.domain.enumeration.dict.AecaEmailDeliveryTypeDictCodes;
import com.aeca.email.repository.base.AecaEmailCodedDictionaryRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий словаря типов рассылки
 */
@Repository
public interface AecaEmailDeliveryTypeDictRepository extends AecaEmailCodedDictionaryRepository<AecaEmailDeliveryTypeDictEntity, AecaEmailDeliveryTypeDictCodes> {
}
