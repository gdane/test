package com.aeca.email.properties.ssl;

public interface AecaEmailEjbcaSslProperties {

    /**
     * Получения путм до хранилища ключей
     *
     * @return путь до хранилища ключей
     */
    String getKeyStorePath();

    /**
     * Получение пути до хранилище доверенных сертификатов
     *
     * @return путь до хранилище доверенных сертификатов
     */
    String getTrustStorePath();


    /**
     * Пароль от хранилища ключей
     *
     * @return пароль от хранилища ключей
     */
    String getKeyStorePassword();

    /**
     * Пароль от хранилища доверенных сертификатов
     *
     * @return аароль от хранилища доверенных сертификатов
     */
    String getTrustStorePassword();
}
