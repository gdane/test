package com.aeca.email.domain.dto.external.aeca.logs.request;

import com.aeca.email.domain.dto.external.aeca.logs.enumeration.AecaLogsServiceNameExternalEnum;
import com.aeca.email.domain.dto.external.aeca.logs.enumeration.AecaLogsUserRoleEnum;
import com.aeca.email.domain.dto.external.aeca.logs.enumeration.dict.AecaLogsActionExternalDictCodes;
import com.aeca.email.domain.dto.external.aeca.logs.pojo.AecaLogsItem;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс запроса фиксации события в журнале
 *
 * @author Aleksandr Rjakhov
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Запрос фиксации события в журнале")
public class AecaLogsCreateLogRequestExternalDto implements Serializable {

    public static final String SYSTEM_USERNAME = "SYSTEM";

    /**
     * Наименование сервиса
     */
    @Schema(description = "Наименование сервиса")
    private final AecaLogsServiceNameExternalEnum serviceName = AecaLogsServiceNameExternalEnum.AECA_EMAIL_SERVICE;

    /**
     * Совершенное действие
     */
    @Schema(description = "Совершенное действие")
    private AecaLogsActionExternalDictCodes action;

    /**
     * Имя пользователя
     */
    @Schema(description = "Имя пользователя")
    private String username = SYSTEM_USERNAME;

    /**
     * Роль пользователя
     */
    @Schema(description = "Роль пользователя")
    private AecaLogsUserRoleEnum role = AecaLogsUserRoleEnum.NONE;

    /**
     * Дополнительное описание события
     */
    @Schema(description = "Дополнительное описание события")
    private String description;

    /**
     * Список изменений
     */
    @Schema(description = "Список изменений")
    private List<AecaLogsItem<?>> attributes = new ArrayList<>();

    /**
     * Статус системного действия
     */
    @Schema(description = "Статус системного действия")
    private boolean system = true;

    public static AecaLogsCreateLogRequestExternalDtoBuilder builder() {
        return new AecaLogsCreateLogRequestExternalDtoBuilder();
    }

    @NoArgsConstructor
    public static class AecaLogsCreateLogRequestExternalDtoBuilder {

        /**
         * Совершенное действие
         */
        private AecaLogsActionExternalDictCodes action;

        /**
         * Имя пользователя
         */
        private String username = SYSTEM_USERNAME;

        /**
         * Роль пользователя
         */
        private AecaLogsUserRoleEnum role = AecaLogsUserRoleEnum.NONE;

        /**
         * Дополнительное описание события
         */
        private String description;

        /**
         * Список изменений
         */
        private List<AecaLogsItem<?>> attributes = new ArrayList<>();

        /**
         * Статус системного действия
         */
        private boolean system = true;

        public AecaLogsCreateLogRequestExternalDtoBuilder action(AecaLogsActionExternalDictCodes code) {
            this.action = code;
            return this;
        }

        public AecaLogsCreateLogRequestExternalDtoBuilder username(String username) {
            this.username = username;
            return this;
        }

        public AecaLogsCreateLogRequestExternalDtoBuilder role(AecaLogsUserRoleEnum role) {
            this.role = role;
            return this;
        }

        public AecaLogsCreateLogRequestExternalDtoBuilder description(String description) {
            this.description = description;
            return this;
        }

        public AecaLogsCreateLogRequestExternalDtoBuilder attributes(List<AecaLogsItem<?>> attributes) {
            this.attributes = attributes;
            return this;
        }

        public AecaLogsCreateLogRequestExternalDtoBuilder system(boolean system) {
            this.system = system;
            return this;
        }

        public AecaLogsCreateLogRequestExternalDto build() {
            return new AecaLogsCreateLogRequestExternalDto(this.action, this.username, this.role, this.description, this.attributes, this.system);
        }
    }
}
