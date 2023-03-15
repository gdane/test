package com.aeca.email.service.converter.dto;

import com.aeca.email.domain.dto.request.AecaEmailCreateDeliveryTemplateRequestDto;
import com.aeca.email.domain.entity.email.AecaEmailDeliveryTemplateEntity;
import com.aeca.email.repository.dictionary.AecaEmailDeliveryTypeDictRepository;
import com.aeca.email.service.converter.AecaEmailConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AecaEmailCreateDeliveryTemplateRequestToEntityConverter implements AecaEmailConverter<AecaEmailCreateDeliveryTemplateRequestDto, AecaEmailDeliveryTemplateEntity> {

    private final AecaEmailDeliveryTypeDictRepository deliveryTypeDictRepository;

    @Override
    public AecaEmailDeliveryTemplateEntity convert(AecaEmailCreateDeliveryTemplateRequestDto data) {
        if (data == null) {
            return null;
        }

        AecaEmailDeliveryTemplateEntity destination = new AecaEmailDeliveryTemplateEntity();

        destination.setTemplateName(data.getTemplateName());
        destination.setSubject(data.getSubject());
        destination.setInterval(data.getInterval());

        destination.setDeliveryType(deliveryTypeDictRepository.getByCode(data.getDeliveryType()));

        return destination;
    }
}
