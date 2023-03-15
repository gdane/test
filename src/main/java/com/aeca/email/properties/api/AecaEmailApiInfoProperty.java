package com.aeca.email.properties.api;

public interface AecaEmailApiInfoProperty {

    /**
     * Получение заголовка
     *
     * @return заголовок
     */
    String getTitle();

    /**
     * Получение описания
     *
     * @return описание
     */
    String getDescription();

    /**
     * Получение версии
     *
     * @return версия
     */
    String getVersion();

    /**
     * Получение термов сервиса
     *
     * @return термы сервиса
     */
    String getTermsOfService();

    /**
     * Получение данных контакта
     *
     * @return данные контакта
     */
    AecaEmailApiInfoContactProperty getContact();

    /**
     * Получение данных лицензии
     *
     * @return данные лицензии
     */
    AecaEmailApiInfoLicenseProperty getLicense();
}
