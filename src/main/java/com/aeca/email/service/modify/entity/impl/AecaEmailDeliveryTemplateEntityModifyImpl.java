package com.aeca.email.service.modify.entity.impl;

import com.aeca.email.domain.entity.email.AecaEmailDeliveryTemplateEntity;
import com.aeca.email.service.modify.entity.AecaEmailDeliveryTemplateEntityModify;
import com.aeca.email.service.modify.entity.base.AecaEmailAbstractEntityModify;
import org.springframework.stereotype.Service;

@Service
public class AecaEmailDeliveryTemplateEntityModifyImpl extends AecaEmailAbstractEntityModify<AecaEmailDeliveryTemplateEntity> implements AecaEmailDeliveryTemplateEntityModify {

    @Override
    public void update(AecaEmailDeliveryTemplateEntity existEntity, AecaEmailDeliveryTemplateEntity updatedEntity) {
        if (existEntity == null || updatedEntity == null) {
            throw getNotExistEntityException(AecaEmailDeliveryTemplateEntity.class);
        }

        existEntity.setTemplateName(updatedEntity.getTemplateName());
        existEntity.setSubject(updatedEntity.getSubject());
        existEntity.setInterval(updatedEntity.getInterval());
        existEntity.setDeliveryType(updatedEntity.getDeliveryType());

    }
}
