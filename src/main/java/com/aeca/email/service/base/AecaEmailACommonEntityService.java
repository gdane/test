package com.aeca.email.service.base;

import com.aeca.email.domain.entity.base.AecaEmailABaseEntity;
import com.aeca.email.exception.AecaEmailExceptionTemplates;
import com.aeca.email.exception.AecaEmailServiceException;
import com.aeca.email.repository.base.AecaEmailCommonRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author Aleksandr Rjakhov
 */
public abstract class AecaEmailACommonEntityService<E extends AecaEmailABaseEntity> implements AecaEmailCommonEntityService<E> {

    protected abstract AecaEmailCommonRepository<E, Long> getRepository();

    @Override
    public List<E> getEntities() {
        return getRepository().findAll();
    }

    @Override
    public E getEntityById(Long sourceId) throws AecaEmailServiceException {
        return getEntityByIdAsOptional(sourceId)
                .orElseThrow(() -> getNotFoundByIdException(sourceId));
    }

    @Override
    public Optional<E> getEntityByIdAsOptional(Long sourceId) {
        return getRepository().findById(sourceId);
    }

    @Override
    public void deleteEntityById(Long sourceId) throws AecaEmailServiceException {
        E entity = getEntityById(sourceId);
        getRepository().delete(entity);
    }

    @Override
    public E save(E entity) {
        return getRepository().save(entity);
    }

    protected AecaEmailServiceException getNotFoundByIdException(Long identity) {
        String errorTemplate = "State with identity ${id} does not exists";
        String internalErrorTemplate = "Не удалось найти сущность по её идентификатору: ${id}";
        Map<String, Object> args = AecaEmailServiceException.argsBuilder()
                .addEntry("id", identity)
                .build();

        return AecaEmailServiceException.fromTemplate(AecaEmailExceptionTemplates.ItemNotFound)
                .errorMessage(errorTemplate, args)
                .internalMessage(internalErrorTemplate, args)
                .build();
    }

    protected AecaEmailServiceException getNotFoundException() {
        String errorTemplate = "Не удалось найти сущность";

        return AecaEmailServiceException.fromTemplate(AecaEmailExceptionTemplates.ItemNotFound)
                .errorMessage(errorTemplate)
                .internalMessage(errorTemplate)
                .build();
    }

    protected AecaEmailServiceException getNotFoundByCommonNameException(String commonName) {
        String errorTemplate = "State with common name ${cn} does not exists";
        String internalErrorTemplate = "Не удалось найти сущность по её общему имени: ${cn}";
        Map<String, Object> args = AecaEmailServiceException.argsBuilder()
                .addEntry("cn", commonName)
                .build();

        return AecaEmailServiceException.fromTemplate(AecaEmailExceptionTemplates.ItemNotFound)
                .errorMessage(errorTemplate, args)
                .internalMessage(internalErrorTemplate, args)
                .build();
    }

    protected AecaEmailServiceException getNotFoundByNameException(String name) {
        String errorTemplate = "State with name ${name} does not exists";
        String internalErrorTemplate = "Не удалось найти сущность по её наименованию: ${name}";
        Map<String, Object> args = AecaEmailServiceException.argsBuilder()
                .addEntry("name", name)
                .build();

        return AecaEmailServiceException.fromTemplate(AecaEmailExceptionTemplates.ItemNotFound)
                .errorMessage(errorTemplate, args)
                .internalMessage(internalErrorTemplate, args)
                .build();
    }
}
