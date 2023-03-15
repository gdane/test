package com.aeca.email.properties.api.impl;

import com.aeca.email.properties.api.AecaEmailApiInfoContactProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Свойства контакта API информации
 */
@Getter
@Setter
@ToString
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "api.info.contact")
public class AecaEmailApiInfoContactPropertyImpl implements AecaEmailApiInfoContactProperty {

    /**
     * Наименование контакта
     */
    private String name;

    /**
     * Электронный адрес контакта
     */
    private String email;

    /**
     * URL контакта
     */
    private String url;
}


