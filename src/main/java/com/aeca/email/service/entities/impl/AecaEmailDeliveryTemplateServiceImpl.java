package com.aeca.email.service.entities.impl;

import com.aeca.email.domain.dto.request.AecaEmailCreateDeliveryTemplateRequestDto;
import com.aeca.email.domain.dto.response.AecaEmailDeliveryTemplateResponseDto;
import com.aeca.email.domain.entity.email.AecaEmailDeliveryTemplateEntity;
import com.aeca.email.domain.enumeration.AecaEmailDeliveryTemplateStatusEnum;
import com.aeca.email.domain.enumeration.dict.AecaEmailDeliveryTypeDictCodes;
import com.aeca.email.repository.AecaEmailDeliveryTemplateRepository;
import com.aeca.email.service.base.AecaEmailACommonEntityService;
import com.aeca.email.service.converter.AecaEmailConverter;
import com.aeca.email.service.entities.AecaEmailDeliveryTemplateService;
import com.aeca.email.service.modify.AecaEmailModify;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AecaEmailDeliveryTemplateServiceImpl extends AecaEmailACommonEntityService<AecaEmailDeliveryTemplateEntity> implements AecaEmailDeliveryTemplateService {
    @Getter
    private final AecaEmailDeliveryTemplateRepository repository;

    private final AecaEmailConverter<AecaEmailDeliveryTemplateEntity, AecaEmailDeliveryTemplateResponseDto> deliveryTemplateToResponseConverter;
    private final AecaEmailConverter<AecaEmailCreateDeliveryTemplateRequestDto, AecaEmailDeliveryTemplateEntity> requestToDeliveryTemplateConverter;

    private final AecaEmailModify<AecaEmailDeliveryTemplateEntity> deliveryTemplateModify;

    @Override
    public AecaEmailDeliveryTemplateResponseDto createDeliveryTemplate(AecaEmailCreateDeliveryTemplateRequestDto request) {
        AecaEmailDeliveryTemplateEntity deliveryTemplate = requestToDeliveryTemplateConverter.convert(request);
        getRepository().save(deliveryTemplate);

        return deliveryTemplateToResponseConverter.convert(deliveryTemplate);
    }

    @Override
    public AecaEmailDeliveryTemplateResponseDto updateDeliveryTemplate(Long deliveryTemplateId, AecaEmailCreateDeliveryTemplateRequestDto request) {
        AecaEmailDeliveryTemplateEntity existDeliveryTemplate = getEntityById(deliveryTemplateId);
        AecaEmailDeliveryTemplateEntity deliveryTemplate = requestToDeliveryTemplateConverter.convert(request);

        deliveryTemplateModify.update(existDeliveryTemplate, deliveryTemplate);
        getRepository().save(existDeliveryTemplate);

        return deliveryTemplateToResponseConverter.convert(existDeliveryTemplate);
    }

    @Override
    public void activateDeliveryTemplate(Long deliveryTemplateId) {
        AecaEmailDeliveryTemplateEntity existDeliveryTemplate = getEntityById(deliveryTemplateId);
        existDeliveryTemplate.setStatus(AecaEmailDeliveryTemplateStatusEnum.ACTIVE);

        getRepository().save(existDeliveryTemplate);
    }

    @Override
    public void deactivateDeliveryTemplate(Long deliveryTemplateId) {
        AecaEmailDeliveryTemplateEntity existDeliveryTemplate = getEntityById(deliveryTemplateId);
        existDeliveryTemplate.setStatus(AecaEmailDeliveryTemplateStatusEnum.INACTIVE);

        getRepository().save(existDeliveryTemplate);
    }

    @Override
    public List<AecaEmailDeliveryTemplateResponseDto> findDeliveryTemplatesByDeliveryType(AecaEmailDeliveryTypeDictCodes deliveryType) {
        List<AecaEmailDeliveryTemplateEntity> entities = getRepository().findByDeliveryTypeCode(deliveryType);

        return entities.stream()
                .map(deliveryTemplateToResponseConverter::convert)
                .collect(Collectors.toList());
    }
}
