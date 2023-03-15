package com.aeca.email.exception.handler;

import com.aeca.email.domain.dto.base.AecaEmailErrorResponse;
import com.aeca.email.exception.AecaEmailServiceException;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AecaEmailFeignExceptionHandler implements ErrorDecoder {

    private final ObjectMapper objectMapper;

    @Override
    public Exception decode(String methodKey, Response response) {

        try {
            AecaEmailErrorResponse errorResponse = objectMapper.readValue(response.body().asInputStream(), AecaEmailErrorResponse.class);
            return AecaEmailServiceException.builder()
                    .status().byCode(errorResponse.getStatus())
                    .errorCode(errorResponse.getCode())
                    .causedBy(errorResponse.getData().getCausedBy())
                    .payloadIdentity(errorResponse.getData().getId())
                    .payloadFields(errorResponse.getData().getFields())
                    .errorMessage(String.format("Ошибка запроса к стороннему сервису. Метод:  %s. Причина: %s.",
                            methodKey,
                            errorResponse.getMessage()))
                    .internalMessage("Ошибка запроса к стороннему сервису")
                    .logException(false)
                    .build();
        } catch (Exception e) {
            String body;

            try {
                body = new BufferedReader(response.body().asReader(StandardCharsets.UTF_8))
                        .lines()
                        .collect(Collectors.joining(" "));
            } catch (IOException io) {
                body = response.toString();
            }

            return AecaEmailServiceException.builder()
                    .status().byCode(response.status())
                    .errorCode(50)
                    .errorMessage("Ошибка запроса к стороннему сервису. Метод: " + methodKey + ".")
                    .internalMessage("Ошибка запроса к стороннему сервису")
                    .causedBy(body)
                    .logException(false)
                    .build();
        }

    }

}
