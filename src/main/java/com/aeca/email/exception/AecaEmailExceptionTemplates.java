package com.aeca.email.exception;

import java.text.MessageFormat;

/**
 * Шаблоны ошибок
 */
public class AecaEmailExceptionTemplates {

    private static String placeholder(String param) {
        return "${" + param + "}";
    }

    /**
     * Неизвестная ошибка
     * <ul>
     *     <li/>Код ответа: {@code 500}
     *     <li/>Внутренний код ошибки: {@code 0}
     *     <li/>Логировать исключение: {@code true}
     *     <li/>Шаблон сообщения: {@code Unknown Error}
     * </ul>
     */
    public static final AecaEmailExceptionTemplate UnknownError = AecaEmailExceptionTemplate.builder()
            .responseStatus(500)
            .errorCode(0)
            .errorMessageTemplate("Unknown Error")
            .logException(true)
            .build();


    /**
     * Ресурс не найден
     * <ul>
     *     <li/>Код ответа: {@code 404}
     *     <li/>Внутренний код ошибки: {@code 1}
     *     <li/>Логировать исключение: {@code false}
     *     <li/>Шаблон сообщения: {@code Item not found}
     * </ul>
     */
    public static final AecaEmailExceptionTemplate ItemNotFound = AecaEmailExceptionTemplate.builder()
            .responseStatus(404)
            .errorCode(1)
            .errorMessageTemplate("Item not found")
            .logException(false)
            .build();

    /**
     * Шаблоны ошибок аутентификации
     */
    public static class AuthenticationTemplates {

        /**
         * Отсутствует значение в обязательном заголовке
         * <ul>
         *     <li/>Код ответа: {@code 400}
         *     <li/>Внутренний код ошибки: {@code 90}
         *     <li/>Логировать исключение: {@code false}
         *     <li>
         *         Шаблон сообщения:
         *         <code>
         *             Authentication failed. Value of required header {@link Args#HeaderName [имя заголовка]} is null or empty
         *         </code>
         *     </li>
         *     <li>
         *         Аргументы:
         *         <ul>
         *             <li/>{@link Args#HeaderName} - имя заголовка
         *         </ul>
         *     </li>
         * </ul>
         */
        public static class RequiredHeaderNullOrEmpty {
            public static final AecaEmailExceptionTemplate Template = AecaEmailExceptionTemplate.builder()
                    .responseStatus(401)
                    .errorCode(90)
                    .logException(false)
                    .errorMessageTemplate(
                            MessageFormat.format("Authentication failed. Value of required header {0} is null or empty",
                                    placeholder("headerName"))
                    ).build();

            public static class Args {
                public static final String HeaderName = "headerName";
            }
        }

        public static final AecaEmailExceptionTemplate userCannotBeAuthenticated = AecaEmailExceptionTemplate.builder()
                .responseStatus(401)
                .errorCode(92)
                .logException(false)
                .errorMessageTemplate("Authentication failed. User cannot be authenticated")
                .build();
    }

    /**
     * Шаблоны ошибок валидации
     */
    public static class ValidationTemplates {

        /**
         * Ошибка валидации
         * <ul>
         *     <li/>Код ответа: {@code 400}
         *     <li/>Внутренний код ошибки: {@code 100}
         *     <li/>Логировать исключение: {@code false}
         *     <li/>Шаблон сообщения: {@code Request validation error}
         * </ul>
         */
        public static final AecaEmailExceptionTemplate ValidationError = AecaEmailExceptionTemplate.builder()
                .responseStatus(400)
                .errorCode(100)
                .errorMessageTemplate("Request validation error")
                .logException(false)
                .build();

        /**
         * Ошибка валидации. Обязательное поле или значение в этом поле не передано
         * <ul>
         *     <li/>Код ответа: {@code 400}
         *     <li/>Внутренний код ошибки: {@code 101}
         *     <li/>Логировать исключение: {@code false}
         *     <li/>Шаблон сообщения: {@code Required field is empty}
         * </ul>
         */
        public static final AecaEmailExceptionTemplate RequiredFieldIsNullOrNotPresent = AecaEmailExceptionTemplate.builder()
                .responseStatus(400)
                .errorCode(101)
                .errorMessageTemplate("Missing required field")
                .logException(false)
                .build();

        /**
         * Ошибка валидации. Значение не может быть пустым
         * <ul>
         *     <li/>Код ответа: {@code 400}
         *     <li/>Внутренний код ошибки: {@code 102}
         *     <li/>Логировать исключение: {@code false}
         *     <li/>Шаблон сообщения: {@code Value cannot be empty}
         * </ul>
         */
        public static final AecaEmailExceptionTemplate FieldValueIsEmpty = AecaEmailExceptionTemplate.builder()
                .responseStatus(400)
                .errorCode(102)
                .errorMessageTemplate("Value cannot be empty")
                .logException(false)
                .build();

        /**
         * Ошибка валидации. Неверный тип значения
         * <ul>
         *     <li/>Код ответа: {@code 400}
         *     <li/>Внутренний код ошибки: {@code 103}
         *     <li/>Логировать исключение: {@code false}
         *     <li/>Шаблон сообщения: {@code Invalid value type}
         * </ul>
         */
        public static final AecaEmailExceptionTemplate InvalidValueType = AecaEmailExceptionTemplate.builder()
                .responseStatus(400)
                .errorCode(103)
                .errorMessageTemplate("Invalid value type")
                .logException(false)
                .build();

        /**
         * Ошибка валидации. Неверное значение
         * <ul>
         *     <li/>Код ответа: {@code 400}
         *     <li/>Внутренний код ошибки: {@code 104}
         *     <li/>Логировать исключение: {@code false}
         *     <li/>Шаблон сообщения: {@code Invalid value}
         * </ul>
         */
        public static final AecaEmailExceptionTemplate InvalidValue = AecaEmailExceptionTemplate.builder()
                .responseStatus(400)
                .errorCode(104)
                .errorMessageTemplate("Invalid value")
                .logException(false)
                .build();
    }

    /**
     * Шаблоны ошибок валидации словарей
     */
    public static class DictValidationTemplates {
        /**
         * Ошибка валидации. Объект словаря пустой
         * <ul>
         *     <li/>Код ответа: {@code 400}
         *     <li/>Внутренний код ошибки: {@code 150}
         *     <li/>Логировать исключение: {@code false}
         *     <li/>Шаблон сообщения: {@code Invalid value}
         * </ul>
         */
        public static final AecaEmailExceptionTemplate EmptyDictEntity = AecaEmailExceptionTemplate.builder()
                .responseStatus(400)
                .errorCode(150)
                .errorMessageTemplate("Объект словаря пустой")
                .logException(false)
                .build();
    }

    /**
     * Шаблоны ошибок валидации словарей
     */
    public static class LicenseValidationTemplates {

        /**
         * Ошибка валидации. Срок лицензии истёк
         * <ul>
         *     <li/>Код ответа: {@code 400}
         *     <li/>Внутренний код ошибки: {@code 170}
         *     <li/>Логировать исключение: {@code false}
         *     <li/>Шаблон сообщения: {@code Token expired}
         * </ul>
         */
        public static final AecaEmailExceptionTemplate LicenseEndDateExpired = AecaEmailExceptionTemplate.builder()
                .responseStatus(400)
                .errorCode(170)
                .errorMessageTemplate("Срок лицензии истёк")
                .logException(false)
                .build();

        /**
         * Ошибка валидации. Лицензия не поддерживается
         * <ul>
         *     <li/>Код ответа: {@code 400}
         *     <li/>Внутренний код ошибки: {@code 171}
         *     <li/>Логировать исключение: {@code false}
         *     <li/>Шаблон сообщения: {@code Unsupported token}
         * </ul>
         */
        public static final AecaEmailExceptionTemplate LicenseDoNotSupport = AecaEmailExceptionTemplate.builder()
                .responseStatus(400)
                .errorCode(171)
                .errorMessageTemplate("Данная лицензия не предназначена для продукта Aladdin Enterprise CA")
                .logException(false)
                .build();

        /**
         * Ошибка валидации. Пустая лицензия
         * <ul>
         *     <li/>Код ответа: {@code 400}
         *     <li/>Внутренний код ошибки: {@code 172}
         *     <li/>Логировать исключение: {@code false}
         *     <li/>Шаблон сообщения: {@code Token cannot be empty}
         * </ul>
         */
        public static final AecaEmailExceptionTemplate EmptyLicense = AecaEmailExceptionTemplate.builder()
                .responseStatus(400)
                .errorCode(172)
                .errorMessageTemplate("Некорректный файл")
                .logException(false)
                .build();

        /**
         * Ошибка валидации. Подпись неверна
         * <ul>
         *     <li/>Код ответа: {@code 400}
         *     <li/>Внутренний код ошибки: {@code 173}
         *     <li/>Логировать исключение: {@code false}
         *     <li/>Шаблон сообщения: {@code Token cannot be empty}
         * </ul>
         */
        public static final AecaEmailExceptionTemplate InvalidLicenseSignature = AecaEmailExceptionTemplate.builder()
                .responseStatus(400)
                .errorCode(173)
                .errorMessageTemplate("Подпись неверна")
                .logException(false)
                .build();

        /**
         * Ошибка валидации. Домены лицензии не равны заданным установкам
         * <ul>
         *     <li/>Код ответа: {@code 400}
         *     <li/>Внутренний код ошибки: {@code 174}
         *     <li/>Логировать исключение: {@code false}
         *     <li/>Шаблон сообщения: {@code Value must be equal to the settings}
         * </ul>
         */
        public static final AecaEmailExceptionTemplate LicenseDomainsNotEqualSettings = AecaEmailExceptionTemplate.builder()
                .responseStatus(400)
                .errorCode(174)
                .errorMessageTemplate("Указанный в лицензии сертификат не соответствует используемому корневому сертификату")
                .logException(false)
                .build();


    }

    /**
     * Шаблоны ошибок дискретных правил пользователя
     */
    public static class DiscreteRightsTemplates {

        public static class DuplicateRightFound {
            /**
             * Ошибка. Обнаружено дублирующее правило
             * <ul>
             *     <li/>Код ответа: {@code 500}
             *     <li/>Внутренний код ошибки: {@code 200}
             *     <li/>Логировать исключение: {@code false}
             *     <li>
             *         Шаблон сообщения:
             *         <code>
             *             Для пользователя
             *             {@link AecaEmailExceptionTemplates.DiscreteRightsTemplates.DuplicateRightFound.Args#AccountId [идентификатор пользователя]}
             *             уже задано правило для группы
             *             {@link AecaEmailExceptionTemplates.DiscreteRightsTemplates.DuplicateRightFound.Args#GroupId [идентификатор группы]}
             *         </code>
             *     </li>
             *     <li>
             *         Аргументы:
             *         <ul>
             *             <li/>{@link AecaEmailExceptionTemplates.DiscreteRightsTemplates.DuplicateRightFound.Args#AccountId} - идентификатор пользователя
             *             <li/>{@link AecaEmailExceptionTemplates.DiscreteRightsTemplates.DuplicateRightFound.Args#GroupId} - идентификатор группы
             *         </ul>
             *     </li>
             * </ul>
             */
            public static final AecaEmailExceptionTemplate Template = AecaEmailExceptionTemplate.builder()
                    .responseStatus(500)
                    .errorCode(200)
                    .errorMessageTemplate(MessageFormat.format("Для пользователя \"{0}\" уже задано правило для группы \"{1}\"",
                            AecaEmailExceptionTemplates.placeholder(Args.AccountId),
                            AecaEmailExceptionTemplates.placeholder(Args.GroupId)))
                    .logException(false)
                    .build();

            public static class Args {
                public static final String AccountId = "accountId";
                public static final String GroupId = "groupId";
            }
        }
    }

    /**
     * Шаблоны ошибок, возникающих при работе с файлами
     */
    public static class FileErrorTemplates {

        /**
         * Ошибка загрузки файла в хранилище
         * <ul>
         *     <li/>Код ответа: {@code 500}
         *     <li/>Внутренний код ошибки: {@code 300}
         *     <li/>Логировать исключение: {@code true}
         *     <li/>Шаблон сообщения: {@code Cannot upload file. Please try again.}
         * </ul>
         */
        public static final AecaEmailExceptionTemplate UploadFileError = AecaEmailExceptionTemplate.builder()
                .responseStatus(500)
                .errorCode(200)
                .errorMessageTemplate("Cannot upload file. Please try again.")
                .logException(true)
                .build();

        /**
         * Файл не найден
         * <ul>
         *     <li/>Код ответа: {@code 404}
         *     <li/>Внутренний код ошибки: {@code 301}
         *     <li/>Логировать исключение: {@code false}
         *     <li/>Шаблон сообщения: {@code File does not exists}
         * </ul>
         */
        public static final AecaEmailExceptionTemplate FileNotFound = AecaEmailExceptionTemplate.builder()
                .responseStatus(404)
                .errorCode(201)
                .errorMessageTemplate("File does not exists")
                .logException(false)
                .build();

        /**
         * Не удается создать файл
         * <ul>
         *     <li/>Код ответа: {@code 500}
         *     <li/>Внутренний код ошибки: {@code 302}
         *     <li/>Логировать исключение: {@code false}
         *     <li/>Шаблон сообщения: {@code File creation error}
         * </ul>
         */
        public static final AecaEmailExceptionTemplate FileCreationError = AecaEmailExceptionTemplate.builder()
                .responseStatus(500)
                .errorCode(202)
                .errorMessageTemplate("File creation error")
                .logException(false)
                .build();

        /**
         * Загружаемый файл превысил лимит размера
         * <ul>
         *     <li/>Код ответа: {@code 500}
         *     <li/>Внутренний код ошибки: {@code 303}
         *     <li/>Логировать исключение: {@code false}
         *     <li/>Шаблон сообщения: {@code File size exceeds limit}
         * </ul>
         */
        public static final AecaEmailExceptionTemplate MaxFileSize = AecaEmailExceptionTemplate.builder()
                .responseStatus(500)
                .errorCode(203)
                .errorMessageTemplate("File size exceeds limit!")
                .logException(false)
                .build();

        public static class FileExtensionNotAllowed {
            /**
             * Загрузка файла с указанным расширением запрещена
             * <ul>
             *     <li/>Код ответа: {@code 400}
             *     <li/>Внутренний код ошибки: {@code 304}
             *     <li/>Логировать исключение: {@code false}
             *     <li>
             *         Шаблон сообщения:
             *         <code>
             *             Uploading a file with extension {@link Args#Extension [Расширешние файла]} is prohibited
             *         </code>
             *     </li>
             *     <li>
             *         Аргументы:
             *         <ul>
             *             <li/>{@link Args#Extension} - расширение загружаемого файла
             *         </ul>
             *     </li>
             * </ul>
             */
            public static final AecaEmailExceptionTemplate Template = AecaEmailExceptionTemplate.builder()
                    .responseStatus(400)
                    .errorCode(204)
                    .errorMessageTemplate(MessageFormat.format("Uploading a file with extension \"{0}\" is prohibited",
                            placeholder(Args.Extension)))
                    .logException(false)
                    .build();

            public static class Args {
                public static final String Extension = "ext";
            }
        }

        /**
         * Загружаемый файл имеет неверную структуру
         * <ul>
         *     <li/>Код ответа: {@code 500}
         *     <li/>Внутренний код ошибки: {@code 305}
         *     <li/>Логировать исключение: {@code false}
         *     <li/>Шаблон сообщения: {@code File structure is invalid, try re-save}
         * </ul>
         */
        public static final AecaEmailExceptionTemplate FileStructureInvalid = AecaEmailExceptionTemplate.builder()
                .responseStatus(500)
                .errorCode(303)
                .errorMessageTemplate("File structure is invalid, try re-save")
                .logException(false)
                .build();

    }
}
