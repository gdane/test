package com.aeca.email.repository.base;

import com.aeca.email.domain.entity.base.AecaEmailABaseDictionaryEntity;
import com.aeca.email.exception.AecaEmailExceptionTemplates;
import com.aeca.email.exception.AecaEmailServiceException;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface AecaEmailDictionaryRepository<T extends AecaEmailABaseDictionaryEntity> extends AecaEmailCommonRepository<T, Long> {
    Optional<T> findByValue(String value);

    default T getEntity(Long id, String fieldName) {
        return findById(id)
                .orElseThrow(() -> AecaEmailServiceException.fromTemplate(AecaEmailExceptionTemplates.ItemNotFound)
                        .payloadFieldName(fieldName)
                        .payloadIdentity(id)
                        .build());
    }


}
