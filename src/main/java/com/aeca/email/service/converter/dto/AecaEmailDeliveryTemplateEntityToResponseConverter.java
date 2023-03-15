package com.aeca.email.service.converter.dto;

import com.aeca.email.domain.dto.response.AecaEmailDeliveryTemplateResponseDto;
import com.aeca.email.domain.entity.email.AecaEmailDeliveryTemplateEntity;
import com.aeca.email.service.converter.AecaEmailConverter;
import com.aeca.email.utils.dictionary.AecaEmailDictUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AecaEmailDeliveryTemplateEntityToResponseConverter implements AecaEmailConverter<AecaEmailDeliveryTemplateEntity, AecaEmailDeliveryTemplateResponseDto> {

    @Override
    public AecaEmailDeliveryTemplateResponseDto convert(AecaEmailDeliveryTemplateEntity data) {
        if (data == null) {
            return null;
        }

        AecaEmailDeliveryTemplateResponseDto destination = new AecaEmailDeliveryTemplateResponseDto();

        destination.setId(data.getId());
        destination.setTemplateName(data.getTemplateName());
        destination.setSubject(data.getSubject());
        destination.setInterval(data.getInterval());
        destination.setStatus(data.getStatus());

        destination.setDeliveryType(AecaEmailDictUtils.getCode(data.getDeliveryType()));

        return destination;

    }
}
