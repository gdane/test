package com.aeca.email.domain.dto.base;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@JsonPropertyOrder({"status", "code", "message", "globalErrors", "fieldErrors"})
public class AecaEmailValidationErrorResponse extends AecaEmailAbstractResponse {

    private int code;
    private String message;
    private final List<GlobalError> globalErrors = new ArrayList<>();
    private final List<FieldGlobalError> fieldErrors = new ArrayList<>();

    public void addError(String message) {
        addError(null, message);
    }

    public void addError(String fieldName, String message) {
        if (fieldName == null || fieldName.isEmpty()) {
            globalErrors.add(GlobalError.of(message));
        } else {
            fieldErrors.add(FieldGlobalError.of(message, fieldName));
        }
    }

    interface Error {
        String getMessage();

        void setMessage(String message);
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor(staticName = "of")
    public static class GlobalError implements Error {
        private String message;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor(staticName = "of")
    @JsonPropertyOrder({"fieldName", "message"})
    public static class FieldGlobalError implements Error {
        private String message;
        private String fieldName;
    }
}
