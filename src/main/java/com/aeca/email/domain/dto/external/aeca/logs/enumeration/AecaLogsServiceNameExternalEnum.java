package com.aeca.email.domain.dto.external.aeca.logs.enumeration;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Перечисление имен используемых сервисов
 *
 * @author Aleksandr Rjakhov
 */
@Schema(description = "Перечисление имен используемых сервисов")
public enum AecaLogsServiceNameExternalEnum {

    AECA_CA_SERVICE,
    AECA_VA_SERVICE,
    AECA_SUBJECTS_SERVICE,
    AECA_EMAIL_SERVICE,
    AECA_CDP_SERVICE,
    AECA_VALIDATION_SERVICE,
    AECA_LOGS_SERVICE
}
