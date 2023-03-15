package com.aeca.email.properties.ssl.impl;

import com.aeca.email.properties.ssl.AecaEmailEjbcaSslProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@NoArgsConstructor
@Configuration
@ConfigurationProperties(prefix = "ejbca-ssl")
public class AecaEmailEjbcaSslPropertiesImpl implements AecaEmailEjbcaSslProperties {

    private String keyStorePath;

    private String trustStorePath;

    private String keyStorePassword;

    private String trustStorePassword;

}
