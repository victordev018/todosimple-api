package com.victordev018.todosimple.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@RequiredArgsConstructor    // for attributes "final"
@JsonInclude(JsonInclude.Include.NON_NULL)      // not include attribute null
public class ErrorResponse {

    // body response
    private final int status;
    private final String  message;
    private String stackTrace;
    private List<ValidationError> errors;

    @Getter
    @Setter
    @RequiredArgsConstructor
    private static class ValidationError {
        private final String field;
        private final String message;
    }

    public void addValidationError(String field, String message){
        if (Objects.isNull(errors)){
            this.errors = new ArrayList<>();
        }
        this.errors.add(new ValidationError(field, message));
    }
}
