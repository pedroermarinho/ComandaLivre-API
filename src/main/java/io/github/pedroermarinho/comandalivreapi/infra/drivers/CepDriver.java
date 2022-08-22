package io.github.pedroermarinho.comandalivreapi.infra.drivers;

import io.github.pedroermarinho.comandalivreapi.domain.record.AddressRecord;

import java.util.Optional;


public interface CepDriver {
    Optional<AddressRecord> findByCep(String cep);
}
