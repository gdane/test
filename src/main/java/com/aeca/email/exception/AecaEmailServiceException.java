package com.aeca.email.exception;

import com.aeca.email.domain.AecaEmailIdentifiable;
import com.aeca.email.domain.dto.base.AecaEmailErrorResponse;
import lombok.Getter;
import org.apache.commons.text.StringSubstitutor;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

@Getter
public class AecaEmailServiceException extends RuntimeException implements AecaEmailExceptionResponse {

    /**
     * Паттерн поиска имени этого класса в стактрейсе
     */
    private static final String STACK_TRACE_CLASS_NAME_PATTERN = AecaEmailServiceException.class.getName().replace(".", "\\.") + "(\\$.*)?";

    public static final int APPLICATION_CODE = 100;

    /**
     * Полезная нагрузка, возвращается в ответе сервиса
     */
    private final AecaEmailErrorResponse.Data payload = new AecaEmailErrorResponse.Data();
    /**
     * Код ответа
     */
    private int status;
    /**
     * Код ошибки [0;99]
     */
    private int errorCode;
    /**
     * Сообщение об ошибке, возвращаемое сервисом
     */
    private String errorMessage;
    /**
     * Сообщение об ошибке, логируемое в обработчике
     */
    private String internalMessage;
    /**
     * Исключение, вызвавшее ошибку
     */
    private Throwable cause;
    /**
     * Флаг лога исключения в обработчике
     */
    private boolean logOnResponse = true;

    public static AecaSubServiceExceptionBuilder builder() {
        return new AecaSubServiceExceptionBuilder();
    }

    /**
     * @return Билдер аргументов для формирования сообщения об ошибке
     */
    public static ArgsBuilder argsBuilder() {
        return new ArgsBuilder();
    }

    /**
     * Заполняет исключение данными из шаблона
     *
     * @param template шаблон исключения
     * @return Билдер исключения
     */
    public static AecaSubServiceExceptionBuilder fromTemplate(AecaEmailExceptionTemplate template) {
        return fromTemplate(template, (Map<String, Object>) null);
    }

    /**
     * Заполняет исключение данными из шаблона. Для формирования сообщения ответа используется
     * {@link AecaEmailExceptionTemplate#getErrorMessageTemplate() шаблон текста} из {@code template}
     * и переданные аргументы
     *
     * @param template шаблон исключения
     * @param args     аргументы сообщения
     * @return Билдер исключения
     */
    public static AecaSubServiceExceptionBuilder fromTemplate(AecaEmailExceptionTemplate template,
                                                              Map<String, Object> args) {
        AecaSubServiceExceptionBuilder builder = builder();

        if (template == null) {
            return builder.status(500)
                    .errorCode(0)
                    .publicAndInternalMessages("Unknown error");
        }

        int responseStatus = template.getResponseStatus();
        int errorCode = template.getErrorCode();
        boolean logException = template.isLogException();
        String messageTemplate = template.getErrorMessageTemplate();

        builder.status(responseStatus);
        builder.errorCode(errorCode);
        builder.logException(logException);
        builder.errorMessage(messageTemplate, args);

        return builder;
    }

    /**
     * Заполняет исключение данными из шаблона. Для формирования сообщения ответа используется
     * {@link AecaEmailExceptionTemplate#getErrorMessageTemplate() шаблон текста} из {@code template}
     * и переданные аргументы
     *
     * @param template        шаблон исключения
     * @param argsBuilderFunc функция сборки аргументов
     * @return Билдер исключения
     */
    public static AecaSubServiceExceptionBuilder fromTemplate(AecaEmailExceptionTemplate template,
                                                              Function<ArgsBuilder, Map<String, Object>> argsBuilderFunc) {
        Map<String, Object> args = argsBuilderFunc.apply(argsBuilder());
        return fromTemplate(template, args);
    }

    /**
     * @return код ошибки, помещаемый в ответ сервиса.
     * Первые 3 знака - код приложения, последние 3 знака - код ошибки
     */
    public int getErrorCode() {
        return APPLICATION_CODE * 1000 + (errorCode % 1000);
    }

    /**
     * @return Внутреннее сообщение с информацией об ошибке
     */
    @Override
    public String getMessage() {
        return getInternalMessage();
    }

    /**
     * Убирает записи создания этого исключения через билдер.
     *
     * <p><b>Оригинал</b></p>
     * <pre>
     * com.aeca.subjects.exception.AecaSubServiceException: test
     * 	    at com.aeca.subjects.exception.AecaSubServiceException$AecaSubServiceExceptionBuilder.<init>(AecaSubServiceException.java:229) ~[main/:na]
     * 	    at com.aeca.subjects.exception.AecaSubServiceException$AecaSubServiceExceptionBuilder.<init>(AecaSubServiceException.java:218) ~[main/:na]
     * 	    at com.aeca.subjects.exception.AecaSubServiceException.builder(AecaSubServiceException.java:95) ~[main/:na]
     * 	    at com.aeca.subjects.exception.AecaSubServiceException.fromTemplate(AecaSubServiceException.java:126) ~[main/:na]
     * 	    at com.aeca.subjects.exception.AecaSubServiceException.fromTemplate(AecaSubServiceException.java:112) ~[main/:na]
     * 	    at com.aeca.subjects.service.impl.AecaSubResourseServiceImpl.getGroup(AecaSubResourseServiceImpl.java:134) ~[main/:na] // <- место генерации исключения
     * 	    ...
     * </pre>
     *
     * <p><b>Модификация</b></p>
     * <pre>
     * com.aeca.subjects.exception.AecaSubServiceException: test
     *      at com.aeca.subjects.service.impl.AecaSubResourseServiceImpl.getGroup(AecaSubResourseServiceImpl.java:249) ~[main/:na] // <- место генерации исключения
     *      ...
     * </pre>
     *
     * @return модифицированный стактрейс
     */
    @Override
    public StackTraceElement[] getStackTrace() {
        StackTraceElement[] stackTrace = super.getStackTrace();

        int copyFrom = 0;
        for (int i = 0; i < stackTrace.length; i++) {
            if (!stackTrace[i].getClassName().matches(STACK_TRACE_CLASS_NAME_PATTERN)) {
                copyFrom = i;
                break;
            }
        }

        StackTraceElement[] modifiedStackTrace = new StackTraceElement[stackTrace.length - copyFrom];
        System.arraycopy(stackTrace, copyFrom, modifiedStackTrace, 0, modifiedStackTrace.length);
        return modifiedStackTrace;
    }

    /**
     * @return Исключение, которое привело к ошибке
     */
    @Override
    public Throwable getCause() {
        return cause;
    }

    @Override
    public AecaEmailErrorResponse getResponse() {
        AecaEmailErrorResponse response = new AecaEmailErrorResponse();

        response.setCode(getErrorCode());
        response.setMessage(getErrorMessage());
        response.setData(getPayload());

        return response;
    }

    /**
     * Билдер аргументов для формирования сообщения об ошибке
     */
    public static class ArgsBuilder {
        /**
         * Добавленные аргументы
         */
        private final Map<String, Object> args = new HashMap<>();

        /**
         * Добавляет аргумент с именем {@code name} и указанным значением {@code value}
         *
         * @param name  имя аргумента
         * @param value значение
         * @return Этот же билдер
         */
        public ArgsBuilder addEntry(String name, Object value) {
            args.put(name, value);
            return this;
        }

        /**
         * @return Словарь аргументов
         */
        public Map<String, Object> build() {
            return args;
        }
    }

    /**
     * Билдер исключений
     */
    public static class AecaSubServiceExceptionBuilder {
        /**
         * Исключение, которое будет возвращено в методе {@link #build()}
         */
        private final AecaEmailServiceException ex;

        private AecaSubServiceExceptionBuilder(AecaEmailServiceException exception) {
            ex = exception;
        }

        /**
         * Имя (ключ) поля, не прошедшего проверку валидации
         */
        private String fieldName = "";

        private AecaSubServiceExceptionBuilder() {
            this(new AecaEmailServiceException());

            ex.status = 500;
            ex.errorCode = -1;
            ex.errorMessage = "Unknown error";
            ex.internalMessage = ex.errorMessage;
        }

        /**
         * Формирует сообщение из шаблона и аргументов.
         *
         * @param template шаблон сообщения
         * @param args     аргументы сообщения
         * @return <ul>
         * <li>Если {@code template} - {@code null} или пустая строка, то возвращает {@code template}</li>
         * <li>Если {@code args} - {@code null} или пустой Map, то возвращает {@code template}</li>
         * <li>Иначе формирует и возвращает сообщение</li>
         * </ul>
         * @apiNote {@code renderMessage("Hello, ${who}!", Collections.singletonMap("who", "World")); //Hello, World!}
         */
        private static String renderMessage(String template, Map<String, Object> args) {
            if (template == null || template.isEmpty() || args == null || args.isEmpty())
                return template;

            return new StringSubstitutor(args).replace(template);
        }

        /**
         * Формирует сообщение из шаблона и аргументов
         *
         * @param template        шаблон сообщения
         * @param argsBuilderFunc функция преобразования аргументов в {@code Map<String, Object>}
         * @return аналогично {@link #renderMessage(String, Map)}
         * @apiNote {@code renderMessage("Hello, ${who}!", (builder) -> builder.addEntry("who", "World").build()) //Hello, World!}
         * @see #renderMessage(String, Function)
         */
        private static String renderMessage(String template,
                                            Function<AecaEmailServiceException.ArgsBuilder, Map<String, Object>> argsBuilderFunc) {
            return renderMessage(template, argsBuilderFunc != null
                    ? argsBuilderFunc.apply(AecaEmailServiceException.argsBuilder())
                    : null);
        }

        /**
         * @return Сконструированный объект исключения
         */
        public AecaEmailServiceException build() {
            return ex;
        }

        /**
         * @return Поставщик: {@code () -> build()}
         */
        public Supplier<AecaEmailServiceException> supply() {
            return this::build;
        }

        /**
         * Собирает и выбрасывает исключение
         */
        public void throwException() {
            throw build();
        }

        /**
         * @return Билдер статуса ответа
         */
        public AecaSubServiceExceptionStatusBuilder status() {
            return new AecaSubServiceExceptionStatusBuilder(this);
        }

        /**
         * Устанавливает в исключение переданный статус ответа
         *
         * @param httpStatusCode статус ответа сервиса
         * @return Этот же билдер
         */
        public AecaSubServiceExceptionBuilder status(int httpStatusCode) {
            return status().byCode(httpStatusCode);
        }

        /**
         * Устанавливает в исключение переданный код ошибки
         *
         * @param code код ошибки [0; 99]
         * @return Этот же билдер
         */
        public AecaSubServiceExceptionBuilder errorCode(int code) {
            ex.errorCode = code;
            return this;
        }

        /**
         * Устанавливает переданное сообщение как сообщение исключения
         * и сообщение, возвращаемое сервисом
         *
         * @param message тескт сообщения
         * @return Этот же билдер
         */
        public AecaSubServiceExceptionBuilder publicAndInternalMessages(String message) {
            return errorMessage(message).internalMessage(message);
        }

        /**
         * Формирует из шаблона сообщение как сообщение исключения
         * и сообщение, возвращаемое сервисом.
         *
         * @param template шаблон сообщения
         * @param args     аргументы
         * @return Этот же билдер
         * @apiNote {@code publicAndInternalMessages("Hello, ${who}!"), Collections.singletonMap("who", "World"); //Hello, World!}
         */
        public AecaSubServiceExceptionBuilder publicAndInternalMessages(String template, Map<String, Object> args) {
            String rendered = renderMessage(template, args);
            return publicAndInternalMessages(rendered);
        }

        /**
         * Формирует из шаблона сообщение как сообщение исключения
         * и сообщение, возвращаемое сервисом.
         *
         * @param template        шаблон сообщения
         * @param argsBuilderFunc функция сборки аргументов
         * @return Этот же билдер
         * @apiNote {@code publicAndInternalMessages("Hello, ${who}!", (builder) -> builder.addEntry("who", "World").build()) //Hello, World!}
         */
        public AecaSubServiceExceptionBuilder publicAndInternalMessages(String template,
                                                                        Function<AecaEmailServiceException.ArgsBuilder, Map<String, Object>> argsBuilderFunc) {
            String rendered = renderMessage(template, argsBuilderFunc);
            return publicAndInternalMessages(rendered);
        }

        /**
         * Устанавливает сообщение, возвращаемое сервисом
         *
         * @param message текст сообщения
         * @return Этот же билдер
         */
        public AecaSubServiceExceptionBuilder errorMessage(String message) {
            ex.errorMessage = message;
            return this;
        }

        /**
         * Формирует из шаблона сообщение, возвращаемое сервисом
         *
         * @param template шаблон сообщения
         * @param args     аргументы
         * @return Этот же билдер
         * @apiNote {@code errorMessage("Hello, ${who}!"), Collections.singletonMap("who", "World"); //Hello, World!}
         */
        public AecaSubServiceExceptionBuilder errorMessage(String template, Map<String, Object> args) {
            String rendered = renderMessage(template, args);
            return errorMessage(rendered);
        }

        /**
         * Формирует из шаблона сообщение, возвращаемое сервисом
         *
         * @param template        шаблон сообщения
         * @param argsBuilderFunc функция сборки аргументов
         * @return Этот же билдер
         * @apiNote {@code errorMessage("Hello, ${who}!", (builder) -> builder.addEntry("who", "World").build()) //Hello, World!}
         */
        public AecaSubServiceExceptionBuilder errorMessage(String template,
                                                           Function<ArgsBuilder, Map<String, Object>> argsBuilderFunc) {
            String rendered = renderMessage(template, argsBuilderFunc);
            return errorMessage(rendered);
        }

        /**
         * Устанавливает логируемое сообщение
         *
         * @param message текст сообщения
         * @return Этот же билдер
         */
        public AecaSubServiceExceptionBuilder internalMessage(String message) {
            ex.internalMessage = message;
            return this;
        }

        /**
         * Формирует из шаблона логируемое сообщение
         *
         * @param template шаблон сообщения
         * @param args     аргументы
         * @return Этот же билдер
         * @apiNote {@code internalMessage("Hello, ${who}!"), Collections.singletonMap("who", "World"); //Hello, World!}
         */
        public AecaSubServiceExceptionBuilder internalMessage(String template, Map<String, Object> args) {
            String rendered = renderMessage(template, args);
            return internalMessage(rendered);
        }

        /**
         * Формирует из шаблона логируемое сообщение
         *
         * @param template        шаблон сообщения
         * @param argsBuilderFunc функция сборки аргументов
         * @return Этот же билдер
         * @apiNote {@code internalMessage("Hello, ${who}!", (builder) -> builder.addEntry("who", "World").build()) //Hello, World!}
         */
        public AecaSubServiceExceptionBuilder internalMessage(String template,
                                                              Function<ArgsBuilder, Map<String, Object>> argsBuilderFunc) {
            String rendered = renderMessage(template, argsBuilderFunc);
            return internalMessage(rendered);
        }

        /**
         * Устанавливает каким исключением была вызвана ошибка
         *
         * @param cause исключение, вызвавшее ошибку
         * @return Этот же билдер
         */
        public AecaSubServiceExceptionBuilder causedBy(Throwable cause) {
            ex.cause = cause;
            ex.payload.setCausedBy(cause.getLocalizedMessage());

            return this;
        }

        /**
         * Устанавливает каким исключением была вызвана ошибка
         *
         * @param message исключение, вызвавшее ошибку
         * @return Этот же билдер
         */
        public AecaSubServiceExceptionBuilder causedBy(String message) {
            ex.payload.setCausedBy(message);

            return this;
        }

        /**
         * Устанавливает флаг логирования исключения в обработчике ошибок
         *
         * @param flag {@code true} - логировать исключение, иначе {@code false}
         * @return Этот же билдер
         */
        public AecaSubServiceExceptionBuilder logException(boolean flag) {
            ex.logOnResponse = flag;
            return this;
        }

        /**
         * Устанавливает флаг логирования исключения в {@code true}
         *
         * @return Этот же билдер
         * @see #logException(boolean)
         */
        public AecaSubServiceExceptionBuilder logException() {
            return logException(true);
        }

        /**
         * Устанавливает флаг логирования исключения в {@code false}
         *
         * @return Этот же билдер
         * @see #logException(boolean)
         */
        public AecaSubServiceExceptionBuilder dontLogException() {
            return logException(false);
        }

        /**
         * Устанавливает идентификатор объекта, обработка которого вызвала ошибку
         *
         * @param id идентификатор объекта
         * @return Этот же билдер
         */
        public AecaSubServiceExceptionBuilder payloadIdentity(Object id) {
            ex.payload.setId(id);
            return this;
        }

        /**
         * Устанавливает идентификатор объекта, обработка которого вызвала ошибку
         *
         * @param entity сущность с идентификатором
         * @return Этот же билдер
         */
        public AecaSubServiceExceptionBuilder payloadIdentity(AecaEmailIdentifiable<?> entity) {
            if (entity == null) {
                return payloadIdentity((Object) null);
            }

            return payloadIdentity(entity.getId());
        }

        /**
         * Устанавливает имя поля, значение которого не прошло проверку
         *
         * @param name имя поля
         * @return Этот же билдер
         */
        public AecaSubServiceExceptionBuilder payloadFieldName(String name) {
            fieldName = name;
            ex.payload.getFields().put(name, null);
            return this;
        }

        /**
         * Устанавливает значение поля, которое (значение) не прошло проверку
         *
         * @param value значение поля
         * @return Этот же билдер
         */
        public AecaSubServiceExceptionBuilder payloadFieldValue(Object value) {
            ex.payload.getFields().put(fieldName, value);
            return this;
        }

        /**
         * Устанавливает поля, которые не прошли проверку
         *
         * @param fields поля
         * @return Этот же билдер
         */
        public AecaSubServiceExceptionBuilder payloadFields(Map<String, Object> fields) {
            ex.payload.setFields(fields);
            return this;
        }
    }

    /**
     * Билдер статуса ответа сервиса
     */
    public static class AecaSubServiceExceptionStatusBuilder {
        private final AecaSubServiceExceptionBuilder builder;

        private AecaSubServiceExceptionStatusBuilder(
                AecaSubServiceExceptionBuilder builder) {
            this.builder = builder;
        }

        /**
         * Устанавливает переданный код ответа
         *
         * @param code код ответа
         * @return Билдер исключения с кодом ответа сервиса {@code code}
         */
        public AecaSubServiceExceptionBuilder byCode(int code) {
            builder.ex.status = code;
            return builder;
        }

        /**
         * Устанавливает код ответа на основе перечисления {@link HttpStatus}
         *
         * @param httpStatus элемент перечисления
         * @return Билдер исключения с кодом ответа сервиса {@link HttpStatus#value()}
         */
        public AecaSubServiceExceptionBuilder byHttpStatus(HttpStatus httpStatus) {
            return httpStatus != null
                    ? byCode(httpStatus.value())
                    : byCode(-1);
        }

        /**
         * @return Билдер исключения с кодом ответа 400
         */
        public AecaSubServiceExceptionBuilder validationFailed() {
            return byCode(400);
        }

        /**
         * @return Билдер исключения с кодом ответа 404
         */
        public AecaSubServiceExceptionBuilder notFound() {
            return byCode(404);
        }

        /**
         * @return Билдер исключения с кодом ответа 409
         */
        public AecaSubServiceExceptionBuilder conflictDuringUpdate() {
            return byCode(409);
        }

        /**
         * @return Билдер исключения с кодом ответа 500
         */
        public AecaSubServiceExceptionBuilder internalServerError() {
            return byCode(500);
        }
    }
}
