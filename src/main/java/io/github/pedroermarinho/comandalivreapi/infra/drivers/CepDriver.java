package io.github.pedroermarinho.comandalivreapi.infra.drivers;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.AddressDTO;

import java.util.Optional;


public interface CepDriver {
    Optional<AddressDTO> findByCep(String cep);
}
