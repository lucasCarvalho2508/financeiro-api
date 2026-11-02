package io.github.lucascarvalho2508.myfinance_api.doMain.service;

import io.github.lucascarvalho2508.myfinance_api.doMain.Mapper.CataoMapper;
import io.github.lucascarvalho2508.myfinance_api.doMain.components.Validation;
import io.github.lucascarvalho2508.myfinance_api.doMain.dtos.request.CartaoForm;
import io.github.lucascarvalho2508.myfinance_api.doMain.dtos.response.CartaoDetlhes;
import io.github.lucascarvalho2508.myfinance_api.doMain.models.Cartao;
import io.github.lucascarvalho2508.myfinance_api.doMain.repository.CartaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class CartaoService {

    @Autowired
    private CartaoRepository repository;
    @Autowired
    private Validation validation;

    private CataoMapper mapper;


    public CartaoDetlhes criar(CartaoForm form) {
        validation.validar(form);
        Cartao entity = mapper.toCartao(form);
        repository.save(entity);
        return mapper.toCartaoDetlhes(entity);
}

    public List<CartaoDetlhes> listar() {
        List<Cartao> cartoes = repository.findAll();
        return cartoes.stream()
                .map(mapper::toCartaoDetlhes)
                .toList();
    }

    @Transactional
    public CartaoDetlhes atualizar(UUID id, CartaoForm form) {
        validation.validar(form);
        Cartao entity = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cartão não encontrado"));
        entity.setNome(form.nome());
        entity.setBandeira(form.bandeira());
        return mapper.toCartaoDetlhes(entity);
    }

    public void deletar(UUID id) {
        Cartao entity = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cartão não encontrado"));
        repository.delete(entity);
    }
}