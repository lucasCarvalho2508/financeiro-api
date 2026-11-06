package io.github.lucascarvalho2508.myfinance_api.doMain.service;

import io.github.lucascarvalho2508.myfinance_api.doMain.Mapper.CartaoMapper;
import io.github.lucascarvalho2508.myfinance_api.doMain.components.Validation;
import io.github.lucascarvalho2508.myfinance_api.doMain.dtos.request.CartaoForm;
import io.github.lucascarvalho2508.myfinance_api.doMain.dtos.response.CartaoDetlhes;
import io.github.lucascarvalho2508.myfinance_api.doMain.exception.ValidationException;
import io.github.lucascarvalho2508.myfinance_api.doMain.models.Cartao;
import io.github.lucascarvalho2508.myfinance_api.doMain.repository.CartaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartaoService {

    @Autowired
    Validation validation;
    @Autowired
    private CartaoRepository repository;
    @Autowired
    private CartaoMapper mapper;


    public CartaoDetlhes criar(CartaoForm form) {
        var result = validation.validar(form);
        if (result.isErros()) {
            throw new ValidationException(result.getErrors());
        }

        Cartao cartao = mapper.toCartao(form);
        repository.save(cartao);
        return mapper.toCartaoDetlhes(cartao);
    }
}