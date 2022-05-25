package com.br.controledelicenca.api.resource;

import com.br.controledelicenca.domain.Licenca;
import com.br.controledelicenca.request.LicencaDto;
import com.br.controledelicenca.service.LicencaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/Licenca")
@RequiredArgsConstructor
@Log4j2
public class LicencaController {

    private final ModelMapper mapper;

    private final LicencaService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LicencaDto salvar(@RequestBody @Valid LicencaDto dto) {
        Licenca entity = mapper.map(dto, Licenca.class);
        entity = service.salvarLicenca(entity);
        return mapper.map(entity, LicencaDto.class);
    }

    @GetMapping
    public Page<LicencaDto> find(LicencaDto dto, Pageable pageRequest) {
        Licenca filter = mapper.map(dto, Licenca.class);
        Page<Licenca> result = service.listarTodasLicencas(filter, pageRequest);
        List<LicencaDto> list = result.getContent()
                .stream()
                .map(entity -> mapper.map(entity, LicencaDto.class))
                .collect(Collectors.toList());
        return new PageImpl<>(list, pageRequest, result.getTotalElements());
    }

    @GetMapping(path = "/{id}")
    public LicencaDto findById(@PathVariable Long id) {
        return service
                .buscaPorId(id)
                .map(licenca -> mapper.map(licenca, LicencaDto.class))
                .orElseThrow((() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        Licenca licenca = service.buscaPorId(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        service.deletarLicenca(licenca);
    }

    @PutMapping("{id}")
    public LicencaDto atualizar(@RequestBody @PathVariable Long id, @RequestBody @Valid LicencaDto dto) {
        return service.buscaPorId(id).map(licenca -> {
            licenca.setDataInicio(dto.getDataInicio());
            licenca.setDataValidade(dto.getDataValidade());
            service.atualizarLicenca(licenca);
            return mapper.map(licenca, LicencaDto.class);
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
