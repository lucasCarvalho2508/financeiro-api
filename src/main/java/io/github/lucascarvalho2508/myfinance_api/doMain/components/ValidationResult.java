package io.github.lucascarvalho2508.myfinance_api.doMain.components;

import io.github.lucascarvalho2508.myfinance_api.doMain.dtos.response.Error;

import java.util.ArrayList;
import java.util.List;

public class ValidationResult {
    List<Error> errors;

    private ValidationResult(List<Error> errors) {
        this.errors = errors;
    }

    public static ValidationResult novo() {
        return new ValidationResult(new ArrayList<>());
    }

    public void addError(Error error) {
        this.errors.add(error);
    }

    public List<Error> getErrors() {
        return errors;
    }

    public boolean isErros() {
        return !errors.isEmpty();
    }
}
