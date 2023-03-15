package com.aeca.email.configuration.modules;

import com.aeca.email.exception.handler.AecaEmailFeignExceptionHandler;
import com.aeca.email.utils.ssl.SSLSocketUtils;
import feign.Client;
import feign.Feign;
import feign.codec.Decoder;
import feign.codec.Encoder;
import lombok.RequiredArgsConstructor;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Service;

import javax.net.ssl.SSLSocketFactory;
import java.io.File;

@Import(FeignClientsConfiguration.class)
@Service
@RequiredArgsConstructor
public class AecaEmailFeignFactory {

    private final Decoder decoder;
    private final Encoder encoder;
    private final Client client;
    private final AecaEmailFeignExceptionHandler exceptionHandler;

    public static Client create(File keystore, String keystorePassword) {
        SSLSocketFactory sslSocketFactory = SSLSocketUtils.getFactory(keystore, keystorePassword);

        return new Client.Default(sslSocketFactory, new NoopHostnameVerifier());
    }

    public <T> T createFeign(Class<T> apiType, String host) {
        return Feign.builder()
                .errorDecoder(exceptionHandler)
                .client(client)
                .decoder(decoder)
                .encoder(encoder)
                .target(apiType, "http://" + host);
    }

    public <T> T createFeign(Class<T> apiType, Client client, String protocol, String host) {
        return Feign.builder()
                .errorDecoder(exceptionHandler)
                .decoder(decoder)
                .encoder(encoder)
                .client(client)
                .target(apiType, protocol + "://" + host);
    }

    public <T> T createFeign(Class<T> apiType, File keystore, String keystorePassword, String host) {
        Client client = create(keystore, keystorePassword);

        return Feign.builder()
                .errorDecoder(exceptionHandler)
                .decoder(decoder)
                .encoder(encoder)
                .client(client)
                .target(apiType, "https://" + host);
    }

    public <T> T createFeign(Class<T> apiType, String host, String port) {
        return Feign.builder()
                .errorDecoder(exceptionHandler)
                .client(client)
                .decoder(decoder)
                .encoder(encoder)
                .target(apiType, "http://" + host + ":" + port);
    }

}