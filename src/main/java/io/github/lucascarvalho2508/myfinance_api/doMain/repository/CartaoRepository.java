package io.github.lucascarvalho2508.myfinance_api.doMain.repository;

import io.github.lucascarvalho2508.myfinance_api.doMain.models.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CartaoRepository extends JpaRepository<Cartao, UUID> {

    Optional<Cartao> findByNome(String nome);
    @Query(""" 
SELECT c FROM Cartao c 
WHERE c.nome = :nome AND (:id IS NULL OR c.id != :id)""")
    List<Cartao> findByNomeAndNotId(@Param("nome") String nome, @Param("id") UUID id);
}
