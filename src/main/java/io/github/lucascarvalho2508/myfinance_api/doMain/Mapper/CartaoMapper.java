package io.github.lucascarvalho2508.myfinance_api.doMain.Mapper;

import io.github.lucascarvalho2508.myfinance_api.doMain.dtos.request.CartaoForm;
import io.github.lucascarvalho2508.myfinance_api.doMain.dtos.response.CartaoDetlhes;
import io.github.lucascarvalho2508.myfinance_api.doMain.models.Cartao;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CartaoMapper {

    Cartao toCartao(CartaoForm form);
    CartaoDetlhes toCartaoDetlhes(Cartao cartao);
    void atualizar(CartaoForm form, @MappingTarget Cartao cartao);
}
