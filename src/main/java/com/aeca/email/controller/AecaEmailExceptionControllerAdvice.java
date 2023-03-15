package com.aeca.email.controller;

import com.aeca.email.service.converter.AecaEmailConverter;
import com.aeca.email.domain.dto.base.AecaEmailErrorResponse;
import com.aeca.email.domain.dto.base.AecaEmailValidationErrorResponse;
import com.aeca.email.exception.AecaEmailExceptionTemplates;
import com.aeca.email.exception.AecaEmailServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.constraints.NotNull;
import java.util.Locale;

@Slf4j
@ControllerAdvice
public class AecaEmailExceptionControllerAdvice {

    private final Locale systemLocale = Locale.getDefault();

    private final AecaEmailConverter<AecaEmailServiceException, AecaEmailErrorResponse> serviceExceptionToErrorResponseConverter;

    private final MessageSource validationMessageSource;

    @Autowired
    public AecaEmailExceptionControllerAdvice(AecaEmailConverter<AecaEmailServiceException, AecaEmailErrorResponse> serviceExceptionToErrorResponseConverter,
                                              MessageSource validationMessageSource) {
        this.serviceExceptionToErrorResponseConverter = serviceExceptionToErrorResponseConverter;
        this.validationMessageSource = validationMessageSource;
    }

    /**
     * Ошибка валидации в теле запроса
     *
     * @param exception объект исключения
     * @return ответ сервиса
     */
    @ExceptionHandler(BindException.class)
    public ResponseEntity<AecaEmailValidationErrorResponse> handleBindingException(BindException exception) {
        BindingResult bindingResult = exception.getBindingResult();
        return buildValidationErrorResponse(-1, "Ошибка валидации тела запроса", bindingResult);
    }

    /**
     * В запросе отсутствует обязательный параметр
     *
     * @param e объект исключения отсутствия обязательного параметра
     * @return ответ сервиса, содержащий данные исключения отсутствия обязательного параметра
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<AecaEmailErrorResponse> handleMissingServletRequestParameterException(
            MissingServletRequestParameterException e) {
        AecaEmailServiceException serviceException = AecaEmailServiceException.fromTemplate(AecaEmailExceptionTemplates.ValidationTemplates.RequiredFieldIsNullOrNotPresent)
                .causedBy(e)
                .logException(false)
                .payloadFieldName(e.getParameterName())
                .build();

        return buildResponse(serviceException);
    }

    /**
     * Ошибка сервиса
     *
     * @param exception объект исключения сервиса
     * @return ответ сервиса, содержащий данные исключения сервиса
     */
    @ExceptionHandler(AecaEmailServiceException.class)
    public ResponseEntity<?> handleServiceException(AecaEmailServiceException exception) {
        return buildResponse(exception);
    }

    /**
     * Неизвестная ошибка (внутренняя ошибка сервиса)
     *
     * @param e объект исключения
     * @return ответ сервиса, содержащий данные исключения
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<AecaEmailErrorResponse> handleDefault(Exception e) {
        AecaEmailServiceException exception = AecaEmailServiceException
                .fromTemplate(AecaEmailExceptionTemplates.UnknownError)
                .causedBy(e)
                .logException(true)
                .build();

        return buildResponse(exception);
    }

    /**
     * Ошибка отсутствия доступа к сервису
     *
     * @param e объект исключения отсутствия доступа
     * @return ответ сервиса, содержащий данные исключения отсутствия доступа
     */
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<AecaEmailErrorResponse> handleAccessDeniedException(AccessDeniedException e) {
        AecaEmailServiceException exception = AecaEmailServiceException.builder()
                .status(403)
                .errorMessage("Insufficient permissions")
                .logException(false)
                .build();

        return buildResponse(exception);
    }

    /**
     * Построение ответа сервиса, содержащий данные исключения
     *
     * @param exception объект исключения
     * @return ответ сервиса, содержащий данные исключения
     */
    private ResponseEntity<AecaEmailErrorResponse> buildResponse(AecaEmailServiceException exception) {
        if (exception.isLogOnResponse()) {
            log.error("Ошибка во время обработки запроса: ", exception);
        }

        AecaEmailErrorResponse responseDto = serviceExceptionToErrorResponseConverter.convert(exception);
        return ResponseEntity.status(responseDto.getStatus()).body(responseDto);
    }


    /**
     * Построение ответа сервиса, содержащего данные ошибки валидации
     *
     * @param code          код ошибки валидации
     * @param message       текст сообщения
     * @param bindingResult результат исключения
     * @return ответа сервиса, содержащий данные ошибки валидации
     */
    private ResponseEntity<AecaEmailValidationErrorResponse> buildValidationErrorResponse(int code, String message, @NotNull BindingResult bindingResult) {
        AecaEmailValidationErrorResponse body = new AecaEmailValidationErrorResponse();
        body.setCode(code);
        body.setMessage(message);
        body.setStatus(HttpStatus.BAD_REQUEST.value());

        bindingResult.getGlobalErrors()
                .stream()
                .map(this::getValidationErrorMessage)
                .forEach(body::addError);

        bindingResult.getFieldErrors()
                .forEach(error -> body.addError(
                        error.getField(),
                        getValidationErrorMessage(error)
                ));

        return ResponseEntity.status(body.getStatus()).body(body);
    }

    /**
     * Получение сообщения ошибки валидации
     *
     * @param error объект ошибки
     * @return сообщение ошибки валидации
     */
    private String getValidationErrorMessage(ObjectError error) {
        return getValidationErrorMessage(error.getCodes(), error.getArguments(), error.getDefaultMessage());
    }

    /**
     * Получение сообщения ошибки валидации
     *
     * @param codes          массив кодов
     * @param arguments      массив аргументов
     * @param defaultMessage сообщение по умолчанию
     * @return сообщение ошибки валидации
     */
    private String getValidationErrorMessage(String[] codes, Object[] arguments, String defaultMessage) {
        if (codes == null) {
            return defaultMessage;
        }
        for (String code : codes) {
            String message = validationMessageSource.getMessage(code, arguments, null, getLocale());
            if (message != null) {
                return message;
            }
        }
        return defaultMessage;
    }

    /**
     * Получение текущей локализации
     *
     * @return объект локализации
     */
    private Locale getLocale() {
        return systemLocale;
    }
}
