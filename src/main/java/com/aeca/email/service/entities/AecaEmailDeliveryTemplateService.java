package com.aeca.email.service.entities;

import com.aeca.email.domain.dto.request.AecaEmailCreateDeliveryTemplateRequestDto;
import com.aeca.email.domain.dto.response.AecaEmailDeliveryTemplateResponseDto;
import com.aeca.email.domain.entity.email.AecaEmailDeliveryTemplateEntity;
import com.aeca.email.domain.enumeration.dict.AecaEmailDeliveryTypeDictCodes;
import com.aeca.email.service.base.AecaEmailCommonEntityService;

import java.util.List;

/**
 * Сервис для работы с сущностью шаблона рассылки
 */
public interface AecaEmailDeliveryTemplateService extends AecaEmailCommonEntityService<AecaEmailDeliveryTemplateEntity> {

    /**
     * Метод выполняет создание нового шаблона рассылки
     *
     * @param request дто запроса на создание шаблона рассылки
     * @return созданный шаблон рассылки
     */
    AecaEmailDeliveryTemplateResponseDto createDeliveryTemplate(AecaEmailCreateDeliveryTemplateRequestDto request);

    /**
     * Метод выполняет обновления шаблона рассылки
     *
     * @param deliveryTemplateId идентификатор обновляемого шаблона рассылки
     * @param request дто запроса на обновление шаблона рассылки
     * @return обновленный шаблон рассылки
     */
    AecaEmailDeliveryTemplateResponseDto updateDeliveryTemplate(Long deliveryTemplateId, AecaEmailCreateDeliveryTemplateRequestDto request);

    /**
     * Метод выполняет активацию шаблона рассылки
     *
     * @param deliveryTemplateId идентификатор шаблона рассылки
     */
    void activateDeliveryTemplate(Long deliveryTemplateId);

    /**
     * Метод выполняет отключение шаблона рассылки
     *
     * @param deliveryTemplateId идентификатор шаблона рассылки
     */
    void deactivateDeliveryTemplate(Long deliveryTemplateId);

    /**
     * Метод выполняет поиск шаблонов рассылки по их типу
     *
     * @param deliveryType тип рассылки
     * @return найденные шаблоны рассылки
     */
    List<AecaEmailDeliveryTemplateResponseDto> findDeliveryTemplatesByDeliveryType(AecaEmailDeliveryTypeDictCodes deliveryType);
}
