package com.aeca.email.domain.dto.pojo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Внутренний класс-обертка сообщения
 */
@Getter
@Setter
@NoArgsConstructor
public class AecaEmailNotification {
    /**
     * Тема письма
     */
    private String subject;
    /**
     * Тело письма
     */
    private String body;
    /**
     * Отправитель
     */
    private String sender;
    /**
     * Получатель
     */
    private String recipient;

    /**
     * Является ли HTML
     */
    private boolean isHtml;
}
