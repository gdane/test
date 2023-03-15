package com.aeca.email.domain.dto.external.aeca.logs.enumeration.dict;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Перечисление кодов событий
 *
 * @author Aleksandr Rjakhov
 */
@Schema(description = "Перечисление кодов событий")
public enum AecaLogsActionExternalDictCodes {

    /**
     * запуск службы
     */
    START_SERVICE,

    /**
     * остановка службы
     */
    STOP_SERVICE,

    /**
     * импорт лицензии
     */
    IMPORT_LICENSE,

    /**
     * ошибка импорта лицензии
     */
    ERROR_IMPORT_LICENSE,

    /**
     * проверка лицензии
     */
    CHECK_LICENSE,

    /**
     * ошибка проверка лицензии
     */
    ERROR_CHECK_LICENSE,

    /**
     * аутентификация пользователя
     */
    USER_AUTHENTICATION,

    /**
     * ошибка аутентификации
     */
    ERROR_USER_AUTHENTICATION,

    /**
     * активация центра сертификации
     */
    ACTIVATION_CENTER_AUTHORITY,

    /**
     * ошибка активации
     */
    ERROR_ACTIVATION_CENTER_AUTHORITY,

    /**
     * создание запроса на сертификат ЦС
     */
    CREATE_REQUEST_TO_CA_CERTIFICATE,

    /**
     * ошибка создания запроса
     */
    ERROR_CREATE_REQUEST_TO_CA_CERTIFICATE,

    /**
     * импорт сертификата центра сертификации
     */
    IMPORT_CA_CERTIFICATE,

    /**
     * ошибка импорта сертификата центра сертификации
     */
    ERROR_IMPORT_CA_CERTIFICATE,

    /**
     * выпуск сертификата
     */
    ISSUE_CERTIFICATE,

    /**
     * ошибка выпуска сертификата
     */
    ERROR_ISSUE_CERTIFICATE,

    /**
     * регистрация центра валидации
     */
    REGISTRATION_CV,

    /**
     * ошибка регистрации
     */
    ERROR_REGISTRATION_CV,

    /**
     * активация OCSP центра валидации
     */
    ACTIVATION_CA_OCSP,

    /**
     * ошибка активации
     */
    ERROR_ACTIVATION_CA_OCSP,

    /**
     * настройка периода CRL
     */
    SETTINGS_CRL_PERIOD,

    /**
     * ошибка настройки
     */
    ERROR_SETTINGS_CRL_PERIOD,

    /**
     * публикация CRL
     */
    PUBLICATION_CRL,

    /**
     * ошибка публикации
     */
    ERROR_PUBLICATION_CRL,

    /**
     * добавление ресурсной системы
     */
    ADD_RESOURCE_SYSTEM,

    /**
     * ошибка добавления
     */
    ERROR_ADD_RESOURCE_SYSTEM,

    /**
     * изменение ресурсной системы
     */
    CHANGE_RESOURCE_SYSTEM,

    /**
     * ошибка изменения
     */
    ERROR_CHANGE_RESOURCE_SYSTEM,

    /**
     * синхронизация ресурса
     */
    SYNC_RESOURCE,

    /**
     * ошибка синхронизации
     */
    ERROR_SYNC_RESOURCE,

    /**
     * создание учетной записи
     */
    CREATE_ACCOUNT,

    /**
     * ошибка создания
     */
    ERROR_CREATE_ACCOUNT,

    /**
     * изменение учетной записи
     */
    CHANGE_ACCOUNT,

    /**
     * ошибка изменения
     */
    ERROR_CHANGE_ACCOUNT,

    /**
     * сохранение прав оператора
     */
    SAVE_OPERATOR_RULES,

    /**
     * ошибка сохранения
     */
    ERROR_SAVE_OPERATOR_RULES,

    /**
     * установка сертификата web-сервера
     */
    INSTALL_WEB_SERVER_CERTIFICATE,

    /**
     * ошибка установки
     */
    ERROR_INSTALL_WEB_SERVER_CERTIFICATE,

    /**
     * изменение списка издателей
     */
    CHANGE_LIST_ISSUERS,

    /**
     * ошибка изменения
     */
    ERROR_CHANGE_LIST_ISSUERS,

    /**
     * перезагрузка web-сервера
     */
    RESTART_WEB_SERVER,

    /**
     * ошибка выполнения перезагрузки
     */
    ERROR_RESTART_WEB_SERVER,

    /**
     * подключение к ключевому носителю
     */
    CONNECTION_TOKEN,

    /**
     * ошибка подключения
     */
    ERROR_CONNECTION_TOKEN,

    /**
     * создание контейнера на ключевом носителе
     */
    CREATE_CONTAINER_TOKEN,

    /**
     * ошибка создания
     */
    ERROR_CREATE_CONTAINER_TOKEN,

    /**
     * запись сертификата на ключевой носитель
     */
    WRITE_CERTIFICATE_TOKEN,

    /**
     * ошибка записи
     */
    ERROR_WRITE_CERTIFICATE_TOKEN,

    /**
     * публикация сертификата в ресурсной системе
     */
    PUBLICATION_CERTIFICATE_TO_RESOURCE_SYSTEM,

    /**
     * ошибка публикации
     */
    ERROR_PUBLICATION_CERTIFICATE_TO_RESOURCE_SYSTEM,

    /**
     * сохранение журнала в CSV
     */
    SAVE_LOG_AS_CSV,

    /**
     * ошибка сохранения
     */
    ERROR_SAVE_LOG_AS_CSV,

    /**
     * генерация CRL
     */
    GENERATION_CRL,

    /**
     * ошибка генерации
     */
    ERROR_GENERATION_CRL,

    /**
     * отправка уведомления на почту
     */
    SEND_EMAIL_NOTE,

    /**
     * ошибка отправки
     */
    ERROR_SEND_EMAIL_NOTE

}
