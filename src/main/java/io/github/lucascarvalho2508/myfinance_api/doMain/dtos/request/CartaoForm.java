package io.github.lucascarvalho2508.myfinance_api.doMain.dtos.request;

import io.github.lucascarvalho2508.myfinance_api.doMain.models.Bandeira;

public record CartaoForm(String nome, Bandeira bandeira) {
}
