package io.github.pedroermarinho.comandalivreapi.infra.drivers;

import java.util.Optional;

import io.github.pedroermarinho.comandalivreapi.domain.entities.Cep;

public interface CepDriver {
    Optional<Cep> findByCep(String cep);
}
