package io.github.lucascarvalho2508.myfinance_api.doMain.components;

import io.github.lucascarvalho2508.myfinance_api.doMain.dtos.request.CartaoForm;
import org.springframework.stereotype.Component;

@Component
public class Validation {

    public void validar(CartaoForm form) {
        if (form.nome() == null || form.nome().isBlank()) {
            throw new IllegalArgumentException("O nome do cartão não pode ser nulo ou vazio.");
        }
        if (form.bandeira() == null) {
            throw new IllegalArgumentException("A bandeira do cartão não pode ser nula.");
        }
    }
}

