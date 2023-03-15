package com.aeca.email.utils.response;

import lombok.experimental.UtilityClass;
import org.springframework.http.ResponseEntity;

import javax.validation.constraints.NotNull;
import java.util.Objects;

@UtilityClass
public class ResponseUtils {

    @NotNull
    public static <T> T getBody(ResponseEntity<T> responseEntity){
        return Objects.requireNonNull(responseEntity.getBody());
    }

}
