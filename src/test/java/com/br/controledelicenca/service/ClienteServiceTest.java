package com.br.controledelicenca.service;

import com.br.controledelicenca.domain.Cliente;
import com.br.controledelicenca.repository.ClienteRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class ClienteServiceTest {

    private ClienteService service;

    @MockBean
    private ClienteRepository repository;

    @BeforeEach
    public void setUp(){
        this.service = new ClienteService(repository);
    }

    @Test
    @DisplayName("Deve salvar um cliente")
    public void salvarClienteTest(){
        // Cenário
        Cliente cliente = criarClienteValido();
        when(repository.save(cliente)).thenReturn(
                (Cliente.builder().id(1L)
                .nome("Cris")
                .cnpj("51.105.340/0001-18\t")
                .email("gustavo_wendel@hotmail.com").build()));

        //Execução
        Cliente clienteSalvo = service.salvarCliente(cliente);

        //Verificação
        assertThat(clienteSalvo.getId()).isNotNull();
        assertThat(clienteSalvo.getNome()).isEqualTo("Cris");
        assertThat(clienteSalvo.getCnpj()).isEqualTo("51.105.340/0001-18\t");
        assertThat(clienteSalvo.getEmail()).isEqualTo("gustavo_wendel@hotmail.com");
    }

    @Test
    @DisplayName("Deve obter um cliente por id")
    public void buscaPorIdTest(){
        //Cenário
        Long id = 1l;
        Cliente cliente = criarClienteValido();
        cliente.setId(id);
        when(repository.findById(id)).thenReturn(Optional.of(cliente));

        //Execução
        Optional<Cliente> clienteEncontrado = service.buscaPorId(id);

        //Verificações
        assertThat(clienteEncontrado.isPresent()).isTrue();
        assertThat(clienteEncontrado.get().getId()).isEqualTo(id);
        assertThat(clienteEncontrado.get().getNome()).isEqualTo(cliente.getNome());
        assertThat(clienteEncontrado.get().getCnpj()).isEqualTo(cliente.getCnpj());
        assertThat(clienteEncontrado.get().getEmail()).isEqualTo(cliente.getEmail());
    }

    @Test
    @DisplayName("Deve retornar vazio ao obter um cliente por id, quando ele não existe na base.")
    public void clienteNotFoundByIdTest(){
        //Cenário
        Long id = 1l;

        when(repository.findById(id)).thenReturn(Optional.empty());

        //Execução
        Optional<Cliente> clienteEncontrado = service.buscaPorId(id);

        //Verificações
        assertThat(clienteEncontrado.isPresent()).isFalse();
    }


    @Test
    @DisplayName("Deve deletar um cliente")
    public void deletarClienteTest() {
        //Cenário
        Cliente cliente = Cliente.builder()
                .id(1L)
                .nome("Wendel")
                .cnpj("42.388.656/0001-38\t")
                .email("wendel@gmail.com").build();

        //Execução
        service.deletarCliente(cliente);

        //Verificações
        verify(repository, times(1)).delete(cliente);
    }

    @Test
    @DisplayName("Deve ocorrer erro ao tentar deletar um cliente inexistente.")
    public void deletarClienteInvalidoTest() {
        //Cenário
        Cliente cliente = new Cliente();
        //Execução
        Assertions.assertThrows(IllegalArgumentException.class, () ->  service.deletarCliente(cliente));
        //Vericação
        verify(repository, never()).delete(cliente);
    }

    @Test
    @DisplayName("Deve atualizar um cliente ")
    public void atualizarClienteTest() {
        //Cenário
        long id = 1L;

        //Cliente a atualizar
        Cliente atualizandoCliente = Cliente.builder().id(id).build();

        //Simulação
        Cliente clienteAtualizado = criarClienteValido();
        clienteAtualizado.setId(id);

        when(repository.save(atualizandoCliente)).thenReturn(clienteAtualizado);

        //Execução
        Cliente cliente = service.atualizarCliente(atualizandoCliente);

        //Verificações
        assertThat(cliente.getId()).isEqualTo(id);
        assertThat(cliente.getNome()).isEqualTo(cliente.getNome());
        assertThat(cliente.getCnpj()).isEqualTo(cliente.getCnpj());
        assertThat(cliente.getEmail()).isEqualTo(cliente.getEmail());

    }

    @Test
    @DisplayName("Deve ocorrer erro ao tentar atualizar um cliente inexistente.")
    public void atualizarClienteInvalidoTest() {
        //Cenário
        Cliente cliente = new Cliente();
        //Execução
        Assertions.assertThrows(IllegalArgumentException.class, () ->  service.atualizarCliente(cliente));
        //Vericação
        verify(repository, never()).save(cliente);
    }

    @Test
    @DisplayName("Deve filtrar os clientes pelas propriedades")
    public void buscarClienteTest() {
        //Cenário
        Cliente cliente = criarClienteValido();

        Pageable pageRequest = PageRequest.of(0, 10);

        List<Cliente> lista = Collections.singletonList(cliente);

        Page<Cliente> page = new PageImpl<>(lista, pageRequest, 1);
        when( repository.findAll(any(Example.class), any(PageRequest.class)) )
                .thenReturn(page);

        //Execução
        Page<Cliente> result = service.listarTodosClientes(cliente, pageRequest);

        //Verificações
        assertThat(result.getTotalElements()).isEqualTo(1);
        assertThat(result.getContent()).isEqualTo(lista);
        assertThat(result.getPageable().getPageNumber()).isZero();
        assertThat(result.getPageable().getPageSize()).isEqualTo(10);
    }

    private Cliente criarClienteValido() {
        return Cliente.builder()
                .nome("Gustavo")
                .cnpj("42.388.646/0001-38\t")
                .email("gustavo_wendel@hotmail.com").build();
    }
}
