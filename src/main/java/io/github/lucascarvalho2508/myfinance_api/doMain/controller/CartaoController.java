package io.github.lucascarvalho2508.myfinance_api.doMain.controller;

import io.github.lucascarvalho2508.myfinance_api.doMain.dtos.request.CartaoForm;
import io.github.lucascarvalho2508.myfinance_api.doMain.dtos.response.CartaoDetlhes;
import io.github.lucascarvalho2508.myfinance_api.doMain.service.CartaoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/cartoes")
public class CartaoController {

    private final CartaoService service;

    public CartaoController(CartaoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<CartaoDetlhes> criar(@Valid @RequestBody CartaoForm form) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(form));
    }

    @GetMapping("{id}")
    public ResponseEntity<CartaoDetlhes> obterDetalhes(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(service.obterDetalhes(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> atualizar(@PathVariable("id") UUID id, @Valid @RequestBody CartaoForm form) {
        service.atualizar(id, form);
        return ResponseEntity.noContent().build();
    }
}
