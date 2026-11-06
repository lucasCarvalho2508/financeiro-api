package io.github.lucascarvalho2508.myfinance_api.doMain.models;

import jakarta.persistence.*;
 import lombok.Getter;
 import lombok.Setter;

 import java.time.LocalDateTime;
import java.util.UUID;
@Getter
@Setter
@Entity
@Table(name = "cartoes")
public class Cartao {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "nome", nullable = false, length = 30)
    private String nome;

    @Enumerated(EnumType.STRING)
    private Bandeira bandeira;

    @Column(name = "data_criacao", nullable = false, updatable = false)
    private LocalDateTime dataCriacao;

    @PrePersist
    public void prepersist(){
        dataCriacao = LocalDateTime.now();
    }
}
