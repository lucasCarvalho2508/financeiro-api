package io.github.lucascarvalho2508.myfinance_api.doMain.components;

import io.github.lucascarvalho2508.myfinance_api.doMain.dtos.request.CartaoForm;
import io.github.lucascarvalho2508.myfinance_api.doMain.dtos.response.Error;
import io.github.lucascarvalho2508.myfinance_api.doMain.repository.CartaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Validation {
    @Autowired
    private CartaoRepository repository;

    public ValidationResult validar(CartaoForm form) {
        var result = ValidationResult.novo();

        if(repository.findByNome(form.nome()).isPresent()) {
            result.addError(
                    new Error("nome",
                            "Já existe um cartão com esse nome")
            );
        }

        return result;
    }
}
