package io.github.lucascarvalho2508.myfinance_api;

import io.github.lucascarvalho2508.myfinance_api.doMain.Mapper.CartaoMapper;
import io.github.lucascarvalho2508.myfinance_api.doMain.dtos.request.CartaoForm;
import io.github.lucascarvalho2508.myfinance_api.doMain.dtos.response.CartaoDetlhes;
import io.github.lucascarvalho2508.myfinance_api.doMain.models.Bandeira;
import io.github.lucascarvalho2508.myfinance_api.doMain.models.Cartao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest(properties = {
        "spring.datasource.url=jdbc:h2:mem:mapper-test;DB_CLOSE_DELAY=-1",
        "spring.jpa.hibernate.ddl-auto=create-drop",
        "spring.sql.init.mode=never"
})
class CartaoMapperTest {

    @Autowired
    private CartaoMapper mapper;

    @Test
    void convertsFormAndEntityAndHandlesNullValues() {
        CartaoForm form = new CartaoForm("Meu cartão", Bandeira.VISA);
        Cartao cartao = mapper.toCartao(form);

        assertEquals(form.nome(), cartao.getNome());
        assertEquals(form.bandeira(), cartao.getBandeira());

        UUID id = UUID.randomUUID();
        LocalDateTime dataCriacao = LocalDateTime.of(2026, 11, 2, 12, 0);
        cartao.setId(id);
        cartao.setDataCriacao(dataCriacao);

        CartaoDetlhes detalhes = mapper.toCartaoDetlhes(cartao);
        assertEquals(id.toString(), detalhes.id());
        assertEquals(cartao.getNome(), detalhes.nome());
        assertEquals(cartao.getBandeira(), detalhes.bandeira());
        assertEquals(dataCriacao, detalhes.dataCriacao());

        mapper.atualizar(cartao, new CartaoForm("Nome atualizado", Bandeira.ELO));
        assertEquals("Nome atualizado", cartao.getNome());
        assertEquals(Bandeira.ELO, cartao.getBandeira());
        assertEquals(id, cartao.getId());
        assertEquals(dataCriacao, cartao.getDataCriacao());

        assertNull(mapper.toCartao(null));
        assertNull(mapper.toCartaoDetlhes(null));
    }
}
