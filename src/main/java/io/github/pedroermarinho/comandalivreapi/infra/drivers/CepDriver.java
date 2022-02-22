package io.github.pedroermarinho.comandalivreapi.infra.drivers;

import java.util.Optional;

import io.github.pedroermarinho.comandalivreapi.domain.entities.AddressEntity;


public interface CepDriver {
    Optional<AddressEntity> findByCep(String cep);
}
