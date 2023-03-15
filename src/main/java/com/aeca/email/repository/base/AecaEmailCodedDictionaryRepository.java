package com.aeca.email.repository.base;

import com.aeca.email.domain.entity.base.AecaEmailABaseCodedDictionaryEntity;
import com.aeca.email.exception.AecaEmailExceptionTemplates;
import com.aeca.email.exception.AecaEmailServiceException;
import org.springframework.data.repository.NoRepositoryBean;
import javax.validation.constraints.NotNull;
import java.util.Optional;

@NoRepositoryBean
public interface AecaEmailCodedDictionaryRepository<T extends AecaEmailABaseCodedDictionaryEntity<E>, E extends Enum<E>> extends AecaEmailDictionaryRepository<T> {

    Optional<T> findByCode(@NotNull E code);

    default T getByCode(@NotNull E code) {
        return findByCode(code)
                .orElseThrow(() -> AecaEmailServiceException.fromTemplate(AecaEmailExceptionTemplates.UnknownError)
                        .internalMessage(String.format("Не удается найти в базе данных сущность с кодом: (%s)=%s", code.getClass(), code))
                        .errorMessage(String.format("Cannot find entity by code: %s", code))
                        .payloadIdentity(code)
                        .build());
    }
}
