package com.aeca.email.repository;

import com.aeca.email.domain.entity.email.AecaEmailDeliveryLogEntity;
import com.aeca.email.domain.entity.email.AecaEmailDeliveryTemplateEntity;
import com.aeca.email.repository.base.AecaEmailCommonRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий журнала рассылки
 */
@Repository
public interface AecaEmailDeliveryLogRepository extends AecaEmailCommonRepository<AecaEmailDeliveryLogEntity, Long> {

    /**
     * Проверяет наличие рассылки указанного типа для сертификата с фингерпринтом
     * @param certificateFingerprint фингерпринт
     * @return результат поиска
     */
    boolean existsByCertificateFingerprintAndDeliveryTemplate(String certificateFingerprint, AecaEmailDeliveryTemplateEntity deliveryTemplate);

}
