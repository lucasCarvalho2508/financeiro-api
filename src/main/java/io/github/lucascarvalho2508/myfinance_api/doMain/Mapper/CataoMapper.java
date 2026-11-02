package io.github.lucascarvalho2508.myfinance_api.doMain.Mapper;

import io.github.lucascarvalho2508.myfinance_api.doMain.dtos.request.CartaoForm;
import io.github.lucascarvalho2508.myfinance_api.doMain.dtos.response.CartaoDetlhes;
import io.github.lucascarvalho2508.myfinance_api.doMain.models.Cartao;

public class CataoMapper {

    public Cartao toCartao(CartaoForm form) {
        Cartao cartao = new Cartao();
        cartao.setNome(form.nome());
        cartao.setBandeira(form.bandeira());
    }

    public CartaoDetlhes toCartaoDetlhes(Cartao cartao) {
        return new CartaoDetlhes(
                cartao.getId().toString(),
                cartao.getNome(),
                cartao.getBandeira(),
                cartao.getDataCriacao()
        );
    }
}
