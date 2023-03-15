package com.aeca.email.exception;

/**
 * Шаблон исключения
 */
public interface AecaEmailExceptionTemplate {

    /**
     * @return Билдер шаблона исключения
     */
    static AecaEmailExceptionTemplateBuilder builder() {
        return new AecaEmailExceptionTemplateBuilder();
    }

    /**
     * @return Статус ответа
     */
    int getResponseStatus();

    /**
     * @return Внутренний код ошибки (3 знака)
     */
    int getErrorCode();

    /**
     * @return Шаблон сообщения (или само сообщение), который будет отправлен пользователю
     */
    String getErrorMessageTemplate();

    /**
     * @return {@code true} - логировать исключение в обработчике ошибок, иначе {@code false}
     */
    boolean isLogException();
}
