package com.aeca.email.properties.api.impl;

import com.aeca.email.properties.api.AecaEmailApiInfoContactProperty;
import com.aeca.email.properties.api.AecaEmailApiInfoLicenseProperty;
import com.aeca.email.properties.api.AecaEmailApiInfoProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

/**
 * Свойства API информации
 */
@Getter
@Setter
@Service
@ToString
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "api.info")
public class AecaEmailApiInfoPropertyImpl implements AecaEmailApiInfoProperty {

    /**
     * Получение заголовка
     */
    private String title;

    /**
     * Получение описания
     */
    private String description;

    /**
     * Получение версии
     */
    private String version;

    /**
     * Получение термов сервиса
     */
    private String termsOfService;

    /**
     * Данные контакта
     */
    private final AecaEmailApiInfoContactProperty contact;

    /**
     * Данные лицензии
     */
    private final AecaEmailApiInfoLicenseProperty license;

    @Autowired
    public AecaEmailApiInfoPropertyImpl(AecaEmailApiInfoContactProperty contact,
                                        AecaEmailApiInfoLicenseProperty license) {
        this.contact = contact;
        this.license = license;
    }
}
