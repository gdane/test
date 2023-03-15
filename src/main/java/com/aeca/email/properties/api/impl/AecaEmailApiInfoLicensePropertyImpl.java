package com.aeca.email.properties.api.impl;

import com.aeca.email.properties.api.AecaEmailApiInfoLicenseProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

/**
 * Свойства лицензии API информации
 */
@Getter
@Setter
@Service
@ToString
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "api.info.license")
public class AecaEmailApiInfoLicensePropertyImpl implements AecaEmailApiInfoLicenseProperty {

    /**
     * Наименование лицензии
     */
    private String name;

    /**
     * URL лицензии
     */
    private String url;
}
