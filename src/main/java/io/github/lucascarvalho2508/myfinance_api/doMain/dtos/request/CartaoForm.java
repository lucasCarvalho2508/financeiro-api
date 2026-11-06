package io.github.lucascarvalho2508.myfinance_api.doMain.dtos.request;

import io.github.lucascarvalho2508.myfinance_api.doMain.models.Bandeira;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CartaoForm(
        @NotBlank(message = "O nome é obrigatório")
        @Size(max = 30, message = "O nome deve possuir no máximo 30 caracteres")
        String nome,
        @NotNull(message = "A bandeira é obrigatória")
        Bandeira bandeira
) {
}
