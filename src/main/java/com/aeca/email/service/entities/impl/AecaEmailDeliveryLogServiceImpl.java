package com.aeca.email.service.entities.impl;

import com.aeca.email.domain.entity.email.AecaEmailDeliveryLogEntity;
import com.aeca.email.repository.AecaEmailDeliveryLogRepository;
import com.aeca.email.service.base.AecaEmailACommonEntityService;
import com.aeca.email.service.entities.AecaEmailDeliveryLogService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AecaEmailDeliveryLogServiceImpl extends AecaEmailACommonEntityService<AecaEmailDeliveryLogEntity> implements AecaEmailDeliveryLogService {

    @Getter
    private final AecaEmailDeliveryLogRepository repository;

}
