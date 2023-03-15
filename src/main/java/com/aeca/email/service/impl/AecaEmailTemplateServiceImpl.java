package com.aeca.email.service.impl;

import com.aeca.email.service.AecaEmailTemplateService;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.expression.ThymeleafEvaluationContext;

import java.util.Locale;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AecaEmailTemplateServiceImpl implements AecaEmailTemplateService, ApplicationContextAware {

    private final SpringTemplateEngine templateEngine;
    private ApplicationContext applicationContext;

    @Override
    public String processTemplate(String template, Map<String, Object> args, Locale locale) {
        Context context = prepareContext(args, locale);
        return templateEngine.process(template, context);
    }

    private Context prepareContext(Map<String, Object> args, Locale locale) {
        Context context = new Context(locale, args);
        context.setVariable(ThymeleafEvaluationContext.THYMELEAF_EVALUATION_CONTEXT_CONTEXT_VARIABLE_NAME,
                new ThymeleafEvaluationContext(applicationContext, null)
        );
        return context;
    }

    @Override
    public void setApplicationContext(@NotNull ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
