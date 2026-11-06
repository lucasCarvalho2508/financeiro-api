package io.github.lucascarvalho2508.myfinance_api.doMain.Mapper;

import io.github.lucascarvalho2508.myfinance_api.doMain.dtos.request.CartaoForm;
import io.github.lucascarvalho2508.myfinance_api.doMain.dtos.response.CartaoDetlhes;
import io.github.lucascarvalho2508.myfinance_api.doMain.models.Cartao;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface CartaoMapper {

    Cartao toCartao(CartaoForm form);
    CartaoDetlhes toCartaoDetlhes(Cartao cartao);
    @BeanMapping(
            nullValuePropertyMappingStrategy =
                    NullValuePropertyMappingStrategy.IGNORE
    )

    void atualizar(@MappingTarget Cartao cartao,CartaoForm  form);


}
