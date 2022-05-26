package com.br.controledelicenca.controller;

import com.br.controledelicenca.api.resource.ClienteController;
import com.br.controledelicenca.domain.Cliente;
import com.br.controledelicenca.request.ClienteDto;
import com.br.controledelicenca.service.ClienteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest(controllers = ClienteController.class)
@AutoConfigureMockMvc
class ClienteControllerTest {
    private static final String CLIENTE_API = "/api/clientes";

    @Autowired
    MockMvc mvc;

    @MockBean
    ClienteService service;

    @Test
    @DisplayName("Deve criar um cleinte com sucesso.")
    void criarClienteTest() throws Exception {

        ClienteDto dto = criarNovoCliente();
        Cliente salvoCliente = Cliente.builder()
                .id(1L)
                .nome("Gustavo Wendel")
                .cnpj("1234567891021")
                .email("gustavo.teste@gmail.com")
                .build();

        BDDMockito.given(service.salvarCliente(Mockito.any(Cliente.class))).willReturn(salvoCliente);
        String json = new ObjectMapper().writeValueAsString(dto);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(CLIENTE_API)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mvc
                .perform(request)
                .andDo(print())
                .andExpect( status().isCreated() )
                .andExpect( jsonPath("id").value(1L) )
                .andExpect( jsonPath("nome").value(dto.getNome()) )
                .andExpect( jsonPath("cnpj").value(dto.getCnpj()) )
                .andExpect( jsonPath("email").value(dto.getEmail()) )


        ;

    }

    private ClienteDto criarNovoCliente() {
        return ClienteDto.builder()
                .nome("Gustavo Wendel")
                .cnpj("1234567891021")
                .email("gustavo.teste@gmail.com")
                .build();
    }

}
