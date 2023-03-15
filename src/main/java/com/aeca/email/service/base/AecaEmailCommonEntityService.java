package com.aeca.email.service.base;

import com.aeca.email.domain.entity.base.AecaEmailABaseEntity;
import com.aeca.email.exception.AecaEmailServiceException;

import java.util.List;
import java.util.Optional;

/**
 * @author Aleksandr Rjakhov
 */
public interface AecaEmailCommonEntityService<E extends AecaEmailABaseEntity> {

    /**
     * Метод получения списка сущностей
     *
     * @return список сущностей
     */
    List<E> getEntities();

    /**
     * Метод получения сущности по её идентификатору
     *
     * @param sourceId идентификатор сущности
     * @return объект класса сущности
     * @throws AecaEmailServiceException объект не найден
     */
    E getEntityById(Long sourceId) throws AecaEmailServiceException;

    /**
     * Метод получения сущности по её идентификатору
     *
     * @param sourceId идентификатор сущности
     * @return объект класса сущности
     */
    Optional<E> getEntityByIdAsOptional(Long sourceId);

    /**
     * Метод удаления сущности по её идентификатору
     *
     * @param sourceId идентификатор сущности
     * @throws AecaEmailServiceException объект не найден
     */
    void deleteEntityById(Long sourceId) throws AecaEmailServiceException;

    /**
     * Метод создания/обновления сущности
     *
     * @param entity объект сущности
     * @return созданный/обновленный объект класса сущности
     */
    E save(E entity);
}
