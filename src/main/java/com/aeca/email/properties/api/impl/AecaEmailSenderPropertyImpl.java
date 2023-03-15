package com.aeca.email.properties.api.impl;

import com.aeca.email.properties.api.AecaEmailSenderProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Data
@Configuration
@ConfigurationProperties(prefix = "email-sender")
@Slf4j
public class AecaEmailSenderPropertyImpl implements AecaEmailSenderProperty {
    /**
     * Хост почтового сервера
     */
    private String host;
    /**
     * Порт
     */
    private int port;
    /**
     * Логин
     */
    private String login;
    /**
     * Пароль
     */
    private String password;
    /**
     * Обратный email
     */
    private String email;
    /**
     * Частота отправки почтовых писем (CRON)
     */
    private String schedule;
    /**
     * Флаг отправки почтовых писем
     */
    private boolean sending;

    private String protocol;
    private boolean smtpAuth;
    private boolean smtpStartTlsEnable;

    @PostConstruct
    private void _logProperties() {
        log.info("\n---\nНастройки отправки почтовых сообщений:" +
                        "\n\tХост: {}" +
                        "\n\tПорт: {}" +
                        "\n\tЛогин: {}" +
                        "\n\tПароль: {}" +
                        "\n\tПротокол: {}" +
                        "\n\tSMTP Auth: {}" +
                        "\n\tStart TLS: {}" +
                        "\n\tОбратный e-mail: {}" +
                        "\n\tЧастота отправки (CRON): {}" +
                        "\n\tОтправлять сообщения: {}" +
                        "\n---",
                getHost(),
                getPort(),
                getLogin(),
                _strFromPassword(getPassword()),
                getProtocol(),
                _strFromBoolean(isSmtpAuth()),
                _strFromBoolean(isSmtpStartTlsEnable()),
                getEmail(),
                getSchedule(),
                _strFromBoolean(isSending())
        );
    }

    private String _strFromBoolean(boolean b) {
        return b ? "да" : "нет";
    }

    private String _strFromPassword(String password) {
        if (password == null) {
            return "<null>";
        } else if (password.equals("")) {
            return "<не установлен>";
        } else return "<установлен>";
    }

}
