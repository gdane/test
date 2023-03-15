package com.aeca.email.controller.api.v2.impl;

import com.aeca.email.controller.api.v2.AecaEmailDeliveryTemplateApi;
import com.aeca.email.domain.dto.base.AecaEmailCollectionResponse;
import com.aeca.email.domain.dto.base.AecaEmailItemResponse;
import com.aeca.email.domain.dto.request.AecaEmailCreateDeliveryTemplateRequestDto;
import com.aeca.email.domain.dto.response.AecaEmailDeliveryTemplateResponseDto;
import com.aeca.email.domain.entity.email.AecaEmailDeliveryTemplateEntity;
import com.aeca.email.domain.enumeration.dict.AecaEmailDeliveryTypeDictCodes;
import com.aeca.email.service.converter.AecaEmailConverter;
import com.aeca.email.service.entities.AecaEmailDeliveryTemplateService;
import com.aeca.email.utils.slice.SliceCollectionUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AecaEmailDeliveryTemplateController implements AecaEmailDeliveryTemplateApi {

    private final AecaEmailDeliveryTemplateService deliveryTemplateService;
    private final AecaEmailConverter<AecaEmailDeliveryTemplateEntity, AecaEmailDeliveryTemplateResponseDto> deliveryTemplateToResponseConverter;

    // POST /
    @Override
    public ResponseEntity<AecaEmailItemResponse<AecaEmailDeliveryTemplateResponseDto>> createDeliveryTemplate(AecaEmailCreateDeliveryTemplateRequestDto request) {
        AecaEmailDeliveryTemplateResponseDto deliveryTemplateDto = deliveryTemplateService.createDeliveryTemplate(request);

        return ResponseEntity.ok(AecaEmailItemResponse.ok(deliveryTemplateDto));
    }

    // PUT /
    @Override
    public ResponseEntity<AecaEmailItemResponse<AecaEmailDeliveryTemplateResponseDto>> editDeliveryTemplate(Long deliveryTemplateId, AecaEmailCreateDeliveryTemplateRequestDto request) {
        AecaEmailDeliveryTemplateResponseDto deliveryTemplateDto = deliveryTemplateService.updateDeliveryTemplate(deliveryTemplateId, request);

        return ResponseEntity.ok(AecaEmailItemResponse.ok(deliveryTemplateDto));
    }

    // DELETE /
    @Override
    public ResponseEntity<Void> deleteDeliveryTemplate(Long deliveryTemplateId) {
        deliveryTemplateService.deleteEntityById(deliveryTemplateId);

        return ResponseEntity.noContent().build();
    }

    // GET /{deliveryType}
    @Override
    public ResponseEntity<AecaEmailCollectionResponse<AecaEmailDeliveryTemplateResponseDto>> getDeliveryTemplatesByDeliveryType(AecaEmailDeliveryTypeDictCodes deliveryType) {
        List<AecaEmailDeliveryTemplateResponseDto> resources = deliveryTemplateService.findDeliveryTemplatesByDeliveryType(deliveryType);
        AecaEmailCollectionResponse<AecaEmailDeliveryTemplateResponseDto> response = SliceCollectionUtils.slice(resources)
                .toResponseBody();

        return ResponseEntity.ok(response);
    }

    // PATCH /activate
    @Override
    public ResponseEntity<Void> activateTemplate(Long deliveryTemplateId) {
        deliveryTemplateService.activateDeliveryTemplate(deliveryTemplateId);

        return ResponseEntity.noContent().build();
    }

    // PATCH /deactivate
    @Override
    public ResponseEntity<Void> deactivateTemplate(Long deliveryTemplateId) {
        deliveryTemplateService.deactivateDeliveryTemplate(deliveryTemplateId);

        return ResponseEntity.noContent().build();
    }

    // GET /byType/{deliveryTemplateId}
    @Override
    public ResponseEntity<AecaEmailItemResponse<AecaEmailDeliveryTemplateResponseDto>> getDeliveryTemplate(Long deliveryTemplateId) {
        AecaEmailDeliveryTemplateEntity deliveryTemplate = deliveryTemplateService.getEntityById(deliveryTemplateId);
        AecaEmailDeliveryTemplateResponseDto response = deliveryTemplateToResponseConverter.convert(deliveryTemplate);

        return ResponseEntity.ok(AecaEmailItemResponse.ok(response));
    }
}
