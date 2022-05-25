package com.br.controledelicenca.repository;

import com.br.controledelicenca.domain.Licenca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LicencaRepository extends JpaRepository <Licenca, Long> {
}
