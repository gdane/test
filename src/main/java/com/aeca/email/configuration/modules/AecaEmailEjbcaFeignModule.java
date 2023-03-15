package com.aeca.email.configuration.modules;

import com.aeca.email.properties.ssl.AecaEmailEjbcaSslProperties;
import feign.Client;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;

@RequiredArgsConstructor
public class AecaEmailEjbcaFeignModule {

    private final AecaEmailEjbcaSslProperties aecaEmailEjbcaSslProperties;

    @Bean
    public Client feignClient() throws FileNotFoundException {
        File keystore = ResourceUtils.getFile(aecaEmailEjbcaSslProperties.getKeyStorePath());
        String keystorePassword = aecaEmailEjbcaSslProperties.getKeyStorePassword();

        return AecaEmailFeignFactory.create(keystore, keystorePassword);
    }

}