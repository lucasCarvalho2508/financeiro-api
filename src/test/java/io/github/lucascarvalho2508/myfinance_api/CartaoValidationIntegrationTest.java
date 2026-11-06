package io.github.lucascarvalho2508.myfinance_api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(properties = {
        "spring.datasource.url=jdbc:h2:mem:validation-test;DB_CLOSE_DELAY=-1",
        "spring.jpa.hibernate.ddl-auto=create-drop",
        "spring.sql.init.mode=never"
})
@AutoConfigureMockMvc
class CartaoValidationIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void rejectsNullName() throws Exception {
        mockMvc.perform(post("/cartoes")
                        .contentType("application/json")
                        .content("{\"bandeira\":\"VISA\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.message").value("Erro de validação"))
                .andExpect(jsonPath("$.errors[0].field").value("nome"))
                .andExpect(jsonPath("$.errors[0].message").value("O nome é obrigatório"));
    }

    @Test
    void rejectsBlankName() throws Exception {
        mockMvc.perform(post("/cartoes")
                        .contentType("application/json")
                        .content("{\"nome\":\"  \",\"bandeira\":\"VISA\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors[0].field").value("nome"))
                .andExpect(jsonPath("$.errors[0].message").value("O nome é obrigatório"));
    }

    @Test
    void rejectsNameLongerThanThirtyCharacters() throws Exception {
        mockMvc.perform(post("/cartoes")
                        .contentType("application/json")
                        .content("{\"nome\":\"1234567890123456789012345678901\",\"bandeira\":\"VISA\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors[0].field").value("nome"))
                .andExpect(jsonPath("$.errors[0].message")
                        .value("O nome deve possuir no máximo 30 caracteres"));
    }

    @Test
    void rejectsNullCardBrand() throws Exception {
        mockMvc.perform(post("/cartoes")
                        .contentType("application/json")
                        .content("{\"nome\":\"Meu cartão\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors[0].field").value("bandeira"))
                .andExpect(jsonPath("$.errors[0].message").value("A bandeira é obrigatória"));
    }

    @Test
    void acceptsValidRequest() throws Exception {
        mockMvc.perform(post("/cartoes")
                        .contentType("application/json")
                        .content("{\"nome\":\"Meu cartão\",\"bandeira\":\"VISA\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.nome").value("Meu cartão"))
                .andExpect(jsonPath("$.bandeira").value("VISA"));
    }
}
