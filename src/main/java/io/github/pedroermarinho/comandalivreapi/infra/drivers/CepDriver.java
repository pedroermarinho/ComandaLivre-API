package io.github.pedroermarinho.comandalivreapi.infra.drivers;

import java.util.Optional;

import io.github.pedroermarinho.comandalivreapi.domain.entities.CepEntity;


public interface CepDriver {
    Optional<CepEntity> findByCep(String cep);
}
