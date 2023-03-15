package com.aeca.email.service.modify.entity.base;

import com.aeca.email.domain.entity.base.AecaEmailABaseEntity;
import com.aeca.email.exception.AecaEmailExceptionTemplates;
import com.aeca.email.exception.AecaEmailServiceException;

import java.util.Map;

public abstract class AecaEmailAbstractEntityModify<T extends AecaEmailABaseEntity> {

    protected AecaEmailServiceException getNotExistEntityException(Class<T> clazz) {
        String errorTemplate = "State ${clazz} is null or does not exist";
        String internalErrorTemplate = "Отсутствует сущность ${clazz}";
        Map<String, Object> args = AecaEmailServiceException.argsBuilder()
                .addEntry("clazz", clazz)
                .build();

        return AecaEmailServiceException.fromTemplate(AecaEmailExceptionTemplates.ItemNotFound)
                .errorMessage(errorTemplate, args)
                .internalMessage(internalErrorTemplate, args)
                .build();
    }
}
