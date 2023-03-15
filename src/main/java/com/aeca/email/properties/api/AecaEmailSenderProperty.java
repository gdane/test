package com.aeca.email.properties.api;

import org.springframework.scheduling.support.CronExpression;

public interface AecaEmailSenderProperty {
    /**
     * @return хост почтового сервера
     */
    String getHost();

    /**
     * @return порт
     */
    int getPort();

    /**
     * @return логин
     */
    String getLogin();

    /**
     * @return пароль
     */
    String getPassword();

    /**
     * @return обратный email
     */
    String getEmail();

    /**
     * @return частота отправки почтовых писем (CRON)
     */
    String getSchedule();

    /**
     * @return флаг отправки почтовых писем
     */
    boolean isSending();

    String getProtocol();

    boolean isSmtpAuth();

    boolean isSmtpStartTlsEnable();
}
