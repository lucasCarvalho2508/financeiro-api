package io.github.lucascarvalho2508.myfinance_api.doMain.exception;

import io.github.lucascarvalho2508.myfinance_api.doMain.dtos.response.Error;
import lombok.Getter;

import java.util.List;

public class ValidationException extends RuntimeException{
    @Getter
    private final List<Error> errors;

    public ValidationException(List<Error> errors) {
        super("Erro de validação");
        this.errors = errors;
    }
}
