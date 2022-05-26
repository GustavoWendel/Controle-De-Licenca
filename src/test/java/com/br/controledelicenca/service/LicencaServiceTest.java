package com.br.controledelicenca.service;

import com.br.controledelicenca.domain.Licenca;
import com.br.controledelicenca.repository.LicencaRepository;
import com.br.controledelicenca.util.ConvertDate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.text.ParseException;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
class LicencaServiceTest {

    private LicencaService service;

    @MockBean
    private LicencaRepository repository;

    @BeforeEach
    public void setUp(){
        this.service = new LicencaService(repository);
    }

    @Test
    @DisplayName("Deve salvar uma Licença")
    public void salvarLicencaTest() throws ParseException {
        // Cenário
        Licenca licenca = criarLicencaValida();
        String dataString = "22/05/2022";
        String dataValidadeString = "20/05/2023";
        Date dataInicio = ConvertDate.parse(dataString, ConvertDate.DateFormat.USA_FORMAT);
        Date dataValidade = ConvertDate.parse(dataValidadeString, ConvertDate.DateFormat.USA_FORMAT);

        when(repository.save(licenca)).thenReturn(
                (Licenca.builder().id(1L)
                        .dataInicio(dataInicio)
                        .dataValidade(dataValidade)
                        .build()));

        //Execução
        Licenca licencaSalva = service.salvarLicenca(licenca);

        //Verificação
        assertThat(licencaSalva.getId()).isNotNull();
        assertThat(licencaSalva.getDataInicio()).isEqualTo("22/05/2022");
        assertThat(licencaSalva.getDataValidade()).isEqualTo("20/05/2023");
    }

    @Test
    @DisplayName("Deve obter um Licenca por id")
    public void buscaPorIdTest() throws ParseException {
        //Cenário
        Long id = 1l;
        Licenca licenca = criarLicencaValida();
        licenca.setId(id);
        when(repository.findById(id)).thenReturn(Optional.of(licenca));

        //Execução
        Optional<Licenca> licencaEncontrado = service.buscaPorId(id);

        //Verificações
        assertThat(licencaEncontrado.isPresent()).isTrue();
        assertThat(licencaEncontrado.get().getId()).isEqualTo(id);
        assertThat(licencaEncontrado.get().getDataInicio()).isEqualTo(licenca.getDataInicio());
        assertThat(licencaEncontrado.get().getDataValidade()).isEqualTo(licenca.getDataInicio());
    }

    @Test
    @DisplayName("Deve retornar vazio ao obter uma Licenca por id, quando ele não existe na base.")
    public void LicencaNotFoundByIdTest(){
        //Cenário
        Long id = 1l;

        when(repository.findById(id)).thenReturn(Optional.empty());

        //Execução
        Optional<Licenca> LicencaEncontrado = service.buscaPorId(id);

        //Verificações
        assertThat(LicencaEncontrado.isPresent()).isFalse();
    }


    @Test
    @DisplayName("Deve deletar um Licenca")
    public void deletarLicencaTest() {
        //Cenário
        Licenca licenca = Licenca.builder()
                .id(1L)
                .build();

        //Execução
        service.deletarLicenca(licenca);

        //Verificações
        verify(repository, times(1)).delete(licenca);
    }

    @Test
    @DisplayName("Deve ocorrer erro ao tentar deletar um Licenca inexistente.")
    public void deletarLicencaInvalidoTest() {
        //Cenário
        Licenca Licenca = new Licenca();
        //Execução
        Assertions.assertThrows(IllegalArgumentException.class, () ->  service.deletarLicenca(Licenca));
        //Vericação
        verify(repository, never()).delete(Licenca);
    }

    @Test
    @DisplayName("Deve atualizar um Licenca ")
    public void atualizarLicencaTest() throws ParseException {
        //Cenário
        long id = 1L;

        //Licenca a atualizar
        Licenca atualizandoLicenca = Licenca.builder().id(id).build();

        //Simulação
        Licenca licencaAtualizada= criarLicencaValida();
        licencaAtualizada.setId(id);

        when(repository.save(atualizandoLicenca)).thenReturn(licencaAtualizada);

        //Execução
        Licenca licenca = service.atualizarLicenca(atualizandoLicenca);

        //Verificações
        assertThat(licenca.getId()).isEqualTo(id);
        assertThat(licenca.getDataInicio()).isEqualTo(licencaAtualizada.getDataInicio());
        assertThat(licenca.getDataValidade()).isEqualTo(licencaAtualizada.getDataValidade());

    }

    @Test
    @DisplayName("Deve ocorrer erro ao tentar atualizar uma Licenca inexistente.")
    public void atualizarLicencaInvalidoTest() {
        //Cenário
        Licenca licenca = new Licenca();
        //Execução
        Assertions.assertThrows(IllegalArgumentException.class, () ->  service.atualizarLicenca(licenca));
        //Vericação
        verify(repository, never()).save(licenca);
    }

    @Test
    @DisplayName("Deve filtrar as Licencas pelas propriedades")
    public void buscarLicencaTest() throws ParseException {
        //Cenário
        Licenca licenca = criarLicencaValida();

        Pageable pageRequest = PageRequest.of(0, 10);

        List<Licenca> lista = Collections.singletonList(licenca);

        Page<Licenca> page = new PageImpl<>(lista, pageRequest, 1);
        when( repository.findAll(any(Example.class), any(PageRequest.class)) )
                .thenReturn(page);

        //Execução
        Page<Licenca> result = service.listarTodasLicencas(licenca, pageRequest);

        //Verificações
        assertThat(result.getTotalElements()).isEqualTo(1);
        assertThat(result.getContent()).isEqualTo(lista);
        assertThat(result.getPageable().getPageNumber()).isZero();
        assertThat(result.getPageable().getPageSize()).isEqualTo(10);
    }

    private Licenca criarLicencaValida() throws ParseException {
        String dataString = "22/05/2022";
        String dataValidadeString = "20/05/2023";
        Date dataInicio = ConvertDate.parse(dataString, ConvertDate.DateFormat.BR_FORMAT);
        Date dataValidade = ConvertDate.parse(dataValidadeString, ConvertDate.DateFormat.BR_FORMAT);
        return Licenca.builder()
                .dataInicio(dataInicio)
                .dataValidade(dataValidade)
                .build();
    }
}
