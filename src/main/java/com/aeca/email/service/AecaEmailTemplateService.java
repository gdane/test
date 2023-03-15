package com.aeca.email.service;

import java.util.Locale;
import java.util.Map;

public interface AecaEmailTemplateService {

    String processTemplate(String template, Map<String, Object> args, Locale locale);
}
