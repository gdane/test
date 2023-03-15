package com.aeca.email.service.converter.exception;

import com.aeca.email.service.converter.AecaEmailConverter;
import com.aeca.email.domain.dto.base.AecaEmailErrorResponse;
import com.aeca.email.exception.AecaEmailServiceException;
import org.springframework.stereotype.Service;

@Service
public class AecaEmailServiceExceptionToErrorResponseConverter implements AecaEmailConverter<AecaEmailServiceException, AecaEmailErrorResponse> {
    @Override
    public AecaEmailErrorResponse convert(AecaEmailServiceException data) {
        if (data == null) {
            return null;
        }

        AecaEmailErrorResponse destination = new AecaEmailErrorResponse();
        destination.setStatus(data.getStatus());
        destination.setCode(data.getErrorCode());
        destination.setMessage(data.getErrorMessage());
        destination.setData(data.getPayload());

        return destination;
    }
}
