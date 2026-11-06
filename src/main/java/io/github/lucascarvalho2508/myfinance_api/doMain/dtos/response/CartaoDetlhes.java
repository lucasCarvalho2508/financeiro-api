package io.github.lucascarvalho2508.myfinance_api.doMain.dtos.response;

import io.github.lucascarvalho2508.myfinance_api.doMain.models.Bandeira;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record CartaoDetlhes(String id,
                            String nome,
                            Bandeira bandeira,
                            @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
                            LocalDateTime dataCriacao) {
}
