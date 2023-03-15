package com.aeca.email.utils.ssl;

import com.aeca.email.exception.AecaEmailServiceException;
import lombok.experimental.UtilityClass;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.ssl.SSLContextBuilder;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import java.io.File;
import java.security.cert.X509Certificate;

@UtilityClass
public class SSLSocketUtils {

    public static SSLContext getContext(File keystore, String keystorePassword) {
        try {
            TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;

            return SSLContextBuilder
                    .create()
                    .loadTrustMaterial(acceptingTrustStrategy)
                    .loadKeyMaterial(
                            keystore,
                            keystorePassword.toCharArray(),
                            keystorePassword.toCharArray())
                    .build();
        } catch (Exception exception) {
            throw AecaEmailServiceException.builder()
                    .causedBy(exception)
                    .logException(true)
                    .build();
        }
    }

    public static SSLSocketFactory getFactory(File keystore, String keystorePassword) {
        return getContext(keystore, keystorePassword)
                .getSocketFactory();
    }

}
