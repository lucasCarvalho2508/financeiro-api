package io.github.lucascarvalho2508.myfinance_api.doMain.repository;

import io.github.lucascarvalho2508.myfinance_api.doMain.models.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CartaoRepository extends JpaRepository<Cartao, UUID> {

    Optional<Cartao> findByNome(String nome);
}
