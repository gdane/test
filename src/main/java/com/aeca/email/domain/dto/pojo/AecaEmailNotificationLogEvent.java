package com.aeca.email.domain.dto.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Класс события отправки уведомления по электронной почте
 *
 * @author Aleksandr Rjakhov
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AecaEmailNotificationLogEvent {

    /**
     * Общее наименование
     */
    private String commonName;

    /**
     * Электронная почта
     */
    private String email;

    /**
     * Шаблон
     */
    private String template;

    public AecaEmailNotificationLogEvent(String email, String template) {
        this.email = email;
        this.template = template;
    }
}
