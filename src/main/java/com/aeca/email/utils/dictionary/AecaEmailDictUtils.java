package com.aeca.email.utils.dictionary;

import com.aeca.email.domain.entity.base.AecaEmailABaseCodedDictionaryEntity;
import com.aeca.email.exception.AecaEmailExceptionTemplates;
import com.aeca.email.exception.AecaEmailServiceException;
import lombok.experimental.UtilityClass;

import java.util.Objects;
import java.util.Optional;

@UtilityClass
public class AecaEmailDictUtils {

    public static <E extends Enum<E>, T extends AecaEmailABaseCodedDictionaryEntity<E>> boolean codeEquals(T dictEntity, E code) {
        return Optional.ofNullable(dictEntity)
                .map(T::getCode)
                .map(dictCode -> Objects.equals(dictCode, code))
                .orElseThrow(() -> AecaEmailServiceException.fromTemplate(AecaEmailExceptionTemplates.DictValidationTemplates.EmptyDictEntity)
                        .build());
    }

    public static <E extends Enum<E>, T extends AecaEmailABaseCodedDictionaryEntity<E>> String getValue(T dictEntity) {
        return Optional.ofNullable(dictEntity)
                .map(AecaEmailABaseCodedDictionaryEntity::getValue)
                .orElse(null);
    }

    public static <E extends Enum<E>, T extends AecaEmailABaseCodedDictionaryEntity<E>> E getCode(T dictEntity) {
        return Optional.ofNullable(dictEntity)
                .map(AecaEmailABaseCodedDictionaryEntity::getCode)
                .orElseThrow(() -> AecaEmailServiceException.fromTemplate(AecaEmailExceptionTemplates.DictValidationTemplates.EmptyDictEntity)
                        .build());
    }

    public static <E extends Enum<E>, T extends AecaEmailABaseCodedDictionaryEntity<E>> E getCodeNullable(T dictEntity) {
        return Optional.ofNullable(dictEntity)
                .map(AecaEmailABaseCodedDictionaryEntity::getCode)
                .orElse(null);
    }

    public static <E extends Enum<E>, T extends AecaEmailABaseCodedDictionaryEntity<E>> E getCodeNullable(Optional<T> dictOptional) {
        return dictOptional
                .map(AecaEmailABaseCodedDictionaryEntity::getCode)
                .orElse(null);
    }

}
