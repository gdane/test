package com.aeca.email.properties.api;

public interface AecaEmailApiInfoContactProperty {

    /**
     * Получение наименования контакта
     *
     * @return Наименование контакта
     */
    String getName();

    /**
     * Получение электронного адреса контакта
     *
     * @return Электронный адрес контакта
     */
    String getEmail();

    /**
     * Получение URL контакта
     *
     * @return URL контакта
     */
    String getUrl();
}
