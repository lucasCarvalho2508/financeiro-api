package io.github.lucascarvalho2508.myfinance_api.doMain.dtos.response;

import io.github.lucascarvalho2508.myfinance_api.doMain.models.Bandeira;

import java.time.LocalDateTime;

public record CartaoDetlhes(String id,
                            String nome,
                            Bandeira bandeira,
                            LocalDateTime dataCriacao) {
}
