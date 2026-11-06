package io.github.lucascarvalho2508.myfinance_api.doMain.service;

import io.github.lucascarvalho2508.myfinance_api.doMain.Mapper.CartaoMapper;
import io.github.lucascarvalho2508.myfinance_api.doMain.components.Validation;
import io.github.lucascarvalho2508.myfinance_api.doMain.dtos.request.CartaoForm;
import io.github.lucascarvalho2508.myfinance_api.doMain.dtos.response.CartaoDetlhes;
import io.github.lucascarvalho2508.myfinance_api.doMain.exception.RegistroNaoEncontradoException;
import io.github.lucascarvalho2508.myfinance_api.doMain.exception.ValidationException;
import io.github.lucascarvalho2508.myfinance_api.doMain.models.Cartao;
import io.github.lucascarvalho2508.myfinance_api.doMain.repository.CartaoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CartaoService {

    private final CartaoRepository repository;
    private final CartaoMapper mapper;
    private final Validation validation;

    public CartaoService(
            CartaoRepository repository,
            CartaoMapper mapper,
            Validation validation
    ) {
        this.repository = repository;
        this.mapper = mapper;
        this.validation = validation;
    }


    public CartaoDetlhes criar(CartaoForm form) {
        var result = validation.validar(form);
        if (result.isErros()) {
            throw new ValidationException(result.getErrors());
        }

        Cartao cartao = mapper.toCartao(form);
        Cartao cartaoSalvo = repository.save(cartao);
        return mapper.toCartaoDetlhes(cartaoSalvo);
    }

    public CartaoDetlhes obterDetalhes(UUID id) {
        CartaoDetlhes cartao = repository
                .findById(id)
                .map(mapper::toCartaoDetlhes)
                .orElseThrow( () -> new RegistroNaoEncontradoException());

        return cartao;
    }

    @Transactional
    public void atualizar(UUID id, CartaoForm form) {
        var entity = repository
                .findById(id)
                .orElseThrow( () -> new RegistroNaoEncontradoException());

        var novo = validation.validar(form);
        if (novo.isErros()) {
            throw new ValidationException(novo.getErrors());
        }

        mapper.atualizar(entity, form);

    }
}