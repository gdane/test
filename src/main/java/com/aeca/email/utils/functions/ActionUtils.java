package com.aeca.email.utils.functions;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.util.function.Consumer;

/**
 * Класс-утилита по работе с действиями над данными
 *
 * @author Aleksandr Rjakhov
 */
@Slf4j
@UtilityClass
public class ActionUtils {

    /**
     * Попытка выполнить действие над данными без выброса исключения
     *
     * @param action выполняемое действие
     * @param data   передаваемые данные
     * @param <T>    тип данных
     */
    public static <T> void withTryAction(Consumer<T> action, T data) {
        try {
            action.accept(data);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
