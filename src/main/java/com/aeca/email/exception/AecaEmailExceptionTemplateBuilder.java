package com.aeca.email.exception;

public class AecaEmailExceptionTemplateBuilder {

    private final AecaEmailExceptionTemplateImpl template = new AecaEmailExceptionTemplateImpl();

    public AecaEmailExceptionTemplateBuilder() {
    }

    public AecaEmailExceptionTemplateBuilder responseStatus(int status) {
        template.setResponseStatus(status);
        return this;
    }

    public AecaEmailExceptionTemplateBuilder errorMessageTemplate(String messageTemplate) {
        template.setErrorMessageTemplate(messageTemplate);
        return this;
    }

    public AecaEmailExceptionTemplateBuilder errorCode(int errorCode) {
        template.setErrorCode(errorCode);
        return this;
    }

    public AecaEmailExceptionTemplateBuilder logException(boolean flag) {
        template.setLogException(flag);
        return this;
    }

    public AecaEmailExceptionTemplate build() {
        return template;
    }
}
