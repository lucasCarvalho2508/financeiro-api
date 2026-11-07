package io.github.lucascarvalho2508.myfinance_api.doMain.components;

import io.github.lucascarvalho2508.myfinance_api.doMain.dtos.request.CartaoForm;
import io.github.lucascarvalho2508.myfinance_api.doMain.dtos.response.Error;
import io.github.lucascarvalho2508.myfinance_api.doMain.repository.CartaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class Validation {
    @Autowired
    private CartaoRepository repository;

    public ValidationResult validar(CartaoForm form, UUID id) {
        var result = ValidationResult.novo();

        if(!repository.findByNomeAndNotId(form.nome(), id).isEmpty()) {
            result.addError(
                    new Error("nome",
                            "Já existe um cartão com esse nome")
            );
        }

        return result;
    }
}
